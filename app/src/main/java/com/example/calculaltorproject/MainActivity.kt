package com.example.calculaltorproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculaltorproject.ui.theme.CalculaltorProjectTheme
import kotlinx.coroutines.launch


val displayBuilder = StringBuilder()
val signsInUse = mutableListOf<Signs>()

fun List<Signs>.unsupportedLastSignsDetected(): Boolean {
    val unsupportedSigns = listOf(Signs.Plus, Signs.Minus, Signs.Multiply, Signs.Divide)
    val signDetected = unsupportedSigns.any { it == last() }
    return signDetected
}

fun List<Signs>.moreThanFifteenSameDigits(newSign: Signs): Boolean {
    val lastFifteenDigits = takeLast(15)
    val reversedLastFifteenDigits = lastFifteenDigits.reversed()
    return reversedLastFifteenDigits.groupBy { it }.any { it.key == newSign && it.value.size == 15 }
}

sealed class Signs(val action: Actions) {
    data object Sum : Signs(Actions.Sign("="))
    data object Delete : Signs(Actions.Sign("C"))
    data object Brackets : Signs(Actions.Sign("( )"))
    data object Percentage : Signs(Actions.Sign("%"))
    data object ClearAll : Signs(Actions.Sign("AC"))
    data object Plus : Signs(Actions.Sign("+"))
    data object Minus : Signs(Actions.Sign("-"))
    data object Divide : Signs(Actions.Sign("/"))
    data object Multiply : Signs(Actions.Sign("*"))
    data object ChangeAdditionNegative : Signs(Actions.Sign("+/-"))
    data object Dot : Signs(Actions.Sign("."))

    data object Nine : Signs(Actions.Number("9"))
    data object Eight : Signs(Actions.Number("8"))
    data object Seven : Signs(Actions.Number("7"))
    data object Six : Signs(Actions.Number("6"))
    data object Five : Signs(Actions.Number("5"))
    data object Four : Signs(Actions.Number("4"))
    data object Three : Signs(Actions.Number("3"))
    data object Two : Signs(Actions.Number("2"))
    data object One : Signs(Actions.Number("1"))
    data object Zero : Signs(Actions.Number("0"))

    data object NotImplementedYet : Signs(Actions.Sign(sign = ""))
}

fun Signs.toVisualRepresentation(): String = when (val action = this.action) {
    is Actions.Sign -> action.sign
    is Actions.Number -> action.number
}


sealed interface Actions {
    data class Sign(val sign: String) : Actions
    data class Number(val number: String) : Actions

}

sealed interface InputErrors {
    data object SignAlreadyUsed : InputErrors
    data object UnsupportedLastSign : InputErrors
    data object NothingToDelete : InputErrors
    data object MoreThanFifteenSameDigits : InputErrors
}

sealed interface SignException {
    data object ExplicitSignException : SignException
}

fun List<Signs>.textComposer(onTextCompleted: (String) -> Unit) {
    forEach { sign ->
        val value = when(val action = sign.action){
            is Actions.Number -> action.number
            is Actions.Sign -> action.sign
        }
        displayBuilder.append(value)
    }

    onTextCompleted(displayBuilder.toString())
    displayBuilder.clear()
}

class UnsupportedSignException : Exception()
class InputSignException(val inputError: InputErrors) : Exception()
fun MutableList<Signs>.signComposer(
    sign: Signs,
    onSignCompleted: (List<Signs>) -> Unit,
    onSignError: (SignException) -> Unit,
    onError: (InputErrors) -> Unit
) {

    try {
        if (sign == Signs.Sum) throw UnsupportedSignException()

        //perform action based on sign delete "C"
        if (sign == Signs.Delete) {
            if (isEmpty()) {
                throw InputSignException(InputErrors.NothingToDelete)
            }

            dropLast(1)
            return
        }

        //perform action based on sign delete "AC"
        if(sign == Signs.ClearAll){
            clear()
            return
        }

        //convert sign to action and perform text computation
        when (sign.action) {
            is Actions.Number -> {

                if (moreThanFifteenSameDigits(sign)) {
                    throw InputSignException(InputErrors.MoreThanFifteenSameDigits)
                }

                add(sign)
            }

            is Actions.Sign -> {

                if (last() == sign) {
                    throw InputSignException(InputErrors.SignAlreadyUsed)
                }

                if (unsupportedLastSignsDetected()) {
                    throw InputSignException(InputErrors.UnsupportedLastSign)
                }

                add(sign)
            }
        }

    } catch (_: UnsupportedSignException) {
        onSignError(SignException.ExplicitSignException)
    }catch (e: InputSignException){
        onError(e.inputError)
    }
    finally {
        onSignCompleted(this)
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorApp()
        }
    }
}

@Composable
fun CalculatorApp(modifier: Modifier = Modifier) {
    CalculaltorProjectTheme {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            modifier = modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->

            var displayValue by remember { mutableStateOf("") }
            val coroutine = rememberCoroutineScope()

            Column(modifier = Modifier.padding(innerPadding)) {
                Displayer(
                    modifier = Modifier.weight(3f),
                    value = displayValue,
                    onDeleteClicked = {
                        signsInUse.signComposer(
                            Signs.Delete,
                            onSignCompleted = {
                                it.textComposer(onTextCompleted = { newText ->
                                    displayValue = newText
                                })
                            },
                            onSignError = {},
                            onError = {})
                    }
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                Interactor(
                    modifier = Modifier.weight(5f),
                    onActionChange = { sign ->
                        when (sign) {
                            Signs.NotImplementedYet -> {
                                coroutine.launch {
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    snackbarHostState.showSnackbar("Not implemented yet.")
                                }
                            }
                            else -> {
                                signsInUse.signComposer(
                                    sign,
                                    onSignCompleted = {
                                       it.textComposer(onTextCompleted = { newText ->
                                           displayValue = newText
                                       })
                                    },
                                    onSignError = {

                                    },
                                    onError = {

                                    })
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Displayer(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    onDeleteClicked: () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.BottomEnd) {
        TextField(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = 72.dp),
            value = value,
            onValueChange = onValueChange,
            readOnly = true
        )
        RoundButton(
            size = 56.dp,
            modifier = Modifier.absoluteOffset((-24).dp, (-8).dp),
            value = "C",
            onClick = onDeleteClicked
        )
    }
}

@Composable
fun Interactor(
    modifier: Modifier = Modifier,
    onActionChange: (Signs) -> Unit
) {
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        FourButtonRow(
            firstButtonValue = Signs.ClearAll,
            secondButtonValue = Signs.NotImplementedYet,
            thirdButtonValue = Signs.NotImplementedYet,
            fourthButtonValue = Signs.Divide,
            onFirstButtonChange = { action ->
                onActionChange(action)
            },
            onSecondButtonChange = { action ->
                onActionChange(action)
            },
            onThirdButtonChange = { action ->
                onActionChange(action)
            },
            onFourthButtonChange = { action ->
                onActionChange(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.Seven,
            secondButtonValue = Signs.Eight,
            thirdButtonValue = Signs.Nine,
            fourthButtonValue = Signs.Multiply,
            onFirstButtonChange = { action ->
                onActionChange(action)
            },
            onSecondButtonChange = { action ->
                onActionChange(action)
            },
            onThirdButtonChange = { action ->
                onActionChange(action)
            },
            onFourthButtonChange = { action ->
                onActionChange(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.Four,
            secondButtonValue = Signs.Five,
            thirdButtonValue = Signs.Six,
            fourthButtonValue = Signs.Minus,
            onFirstButtonChange = { action ->
                onActionChange(action)
            },
            onSecondButtonChange = { action ->
                onActionChange(action)
            },
            onThirdButtonChange = { action ->
                onActionChange(action)
            },
            onFourthButtonChange = { action ->
                onActionChange(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.One,
            secondButtonValue = Signs.Two,
            thirdButtonValue = Signs.Three,
            fourthButtonValue = Signs.Plus,
            onFirstButtonChange = { action ->
                onActionChange(action)
            },
            onSecondButtonChange = { action ->
                onActionChange(action)
            },
            onThirdButtonChange = { action ->
                onActionChange(action)
            },
            onFourthButtonChange = { action ->
                onActionChange(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.NotImplementedYet,
            secondButtonValue = Signs.Zero,
            thirdButtonValue = Signs.NotImplementedYet,
            fourthButtonValue = Signs.Sum,
            onFirstButtonChange = { action ->
                onActionChange(action)
            },
            onSecondButtonChange = { action ->
                onActionChange(action)
            },
            onThirdButtonChange = { action ->
                onActionChange(action)
            },
            onFourthButtonChange = { action ->
                onActionChange(action)
            },
        )
    }
}

@Composable
fun FourButtonRow(
    modifier: Modifier = Modifier,
    firstButtonValue: Signs,
    secondButtonValue: Signs,
    thirdButtonValue: Signs,
    fourthButtonValue: Signs,
    onFirstButtonChange: (Signs) -> Unit,
    onSecondButtonChange: (Signs) -> Unit,
    onThirdButtonChange: (Signs) -> Unit,
    onFourthButtonChange: (Signs) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        RoundButton(
            value = firstButtonValue.toVisualRepresentation(),
            onClick = { onFirstButtonChange(firstButtonValue) })
        RoundButton(
            value = secondButtonValue.toVisualRepresentation(),
            onClick = { onSecondButtonChange(secondButtonValue) })
        RoundButton(
            value = thirdButtonValue.toVisualRepresentation(),
            onClick = { onThirdButtonChange(thirdButtonValue) })
        RoundButton(
            value = fourthButtonValue.toVisualRepresentation(),
            onClick = { onFourthButtonChange(fourthButtonValue) })
    }
}

@Preview
@Composable
private fun FourButtonRowPreview() {
    CalculaltorProjectTheme {
        FourButtonRow(
            firstButtonValue = Signs.One,
            secondButtonValue = Signs.Two,
            thirdButtonValue = Signs.Three,
            fourthButtonValue = Signs.Plus,
            onFirstButtonChange = {},
            onSecondButtonChange = {},
            onThirdButtonChange = {},
            onFourthButtonChange = {},
        )
    }
}

@Composable
fun RoundButton(
    modifier: Modifier = Modifier,
    size: Dp = 72.dp,
    value: String,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.size(size, size),
        contentPadding = PaddingValues(0.dp, 0.dp, 0.dp, 0.dp),
        onClick = onClick,
        shape = CircleShape
    ) {
        Text(text = value, textAlign = TextAlign.Center, fontSize = 24.sp)
    }
}

@Preview
@Composable
private fun RoundButtonPreview() {
    CalculaltorProjectTheme {
        RoundButton(value = "+", onClick = {})
    }
}