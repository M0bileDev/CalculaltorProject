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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculaltorproject.ui.theme.CalculaltorProjectTheme

val displayBuilder = StringBuilder()

enum class Actions {
    CLEAR_ALL,
    SUM
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
        Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->

            var displayValue by remember { mutableStateOf("") }

            Column(modifier = Modifier.padding(innerPadding)) {
                Displayer(
                    modifier = Modifier.weight(3f),
                    value = displayValue,
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                Interactor(
                    modifier = Modifier.weight(5f),
                    onValueChange = { newValue ->
                        displayBuilder.clear()
                        displayBuilder.append(displayValue).append(newValue)
                        displayValue = displayBuilder.toString()
                    },
                    onActionChange = { newAction ->
                        when (newAction) {
                            Actions.CLEAR_ALL -> {
                                displayValue = ""
                            }

                            Actions.SUM -> {
                                TODO()
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
            value = "C"
        ) { }
    }
}

@Composable
fun Interactor(
    modifier: Modifier = Modifier, onValueChange: (String) -> Unit,
    onActionChange: (Actions) -> Unit
) {
    Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceEvenly) {
        FourButtonRow(
            firstButtonValue = "AC",
            secondButtonValue = "( )",
            thirdButtonValue = "%",
            fourthButtonValue = "/",
            onFirstButtonChange = { _ ->
                onActionChange(Actions.CLEAR_ALL)
            },
            onSecondButtonChange = { value ->
//                onValueChange(value)
            },
            onThirdButtonChange = { value ->
//                onValueChange(value)
            },
            onFourthButtonChange = { value ->
                onValueChange(value)
            },
        )
        FourButtonRow(
            firstButtonValue = "7",
            secondButtonValue = "8",
            thirdButtonValue = "9",
            fourthButtonValue = "x",
            onFirstButtonChange = { value ->
                onValueChange(value)
            },
            onSecondButtonChange = { value ->
                onValueChange(value)
            },
            onThirdButtonChange = { value ->
                onValueChange(value)
            },
            onFourthButtonChange = { value ->
                onValueChange(value)
            },
        )
        FourButtonRow(
            firstButtonValue = "4",
            secondButtonValue = "5",
            thirdButtonValue = "6",
            fourthButtonValue = "-",
            onFirstButtonChange = { value ->
                onValueChange(value)
            },
            onSecondButtonChange = { value ->
                onValueChange(value)
            },
            onThirdButtonChange = { value ->
                onValueChange(value)
            },
            onFourthButtonChange = { value ->
                onValueChange(value)
            },
        )
        FourButtonRow(
            firstButtonValue = "1",
            secondButtonValue = "2",
            thirdButtonValue = "3",
            fourthButtonValue = "+",
            onFirstButtonChange = { _ ->
                onActionChange(Actions.CLEAR_ALL)
            },
            onSecondButtonChange = { value ->
                onValueChange(value)
            },
            onThirdButtonChange = { value ->
                onValueChange(value)
            },
            onFourthButtonChange = { value ->
                onValueChange(value)
            },
        )
        FourButtonRow(
            firstButtonValue = "+/-",
            secondButtonValue = "0",
            thirdButtonValue = ".",
            fourthButtonValue = "=",
            onFirstButtonChange = { _ ->
//                onActionChange(Actions.CLEAR_ALL)
            },
            onSecondButtonChange = { value ->
                onValueChange(value)
            },
            onThirdButtonChange = { value ->
//                onValueChange(value)
            },
            onFourthButtonChange = { _ ->
                onActionChange(Actions.SUM)
            },
        )
    }
}

@Composable
fun FourButtonRow(
    modifier: Modifier = Modifier,
    firstButtonValue: String,
    secondButtonValue: String,
    thirdButtonValue: String,
    fourthButtonValue: String,
    onFirstButtonChange: (String) -> Unit,
    onSecondButtonChange: (String) -> Unit,
    onThirdButtonChange: (String) -> Unit,
    onFourthButtonChange: (String) -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        RoundButton(
            value = firstButtonValue,
            onClick = { onFirstButtonChange(firstButtonValue) })
        RoundButton(
            value = secondButtonValue,
            onClick = { onSecondButtonChange(secondButtonValue) })
        RoundButton(
            value = thirdButtonValue,
            onClick = { onThirdButtonChange(thirdButtonValue) })
        RoundButton(
            value = fourthButtonValue,
            onClick = { onFourthButtonChange(fourthButtonValue) })
    }
}

@Preview
@Composable
private fun FourButtonRowPreview() {
    CalculaltorProjectTheme {
        FourButtonRow(
            modifier = Modifier.Companion, "c", "( )",
            "%", "/", {}, {}, {}, {})
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