package com.example.calculaltorproject.presentation.screen.interactor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.components.FourButtonRow

@Composable
fun Interactor(
    modifier: Modifier = Modifier,
    onActionChange: (Signs) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly) {
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