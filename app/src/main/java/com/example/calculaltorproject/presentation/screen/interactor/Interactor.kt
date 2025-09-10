package com.example.calculaltorproject.presentation.screen.interactor

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.components.FourButtonRow

@Composable
fun Interactor(
    modifier: Modifier = Modifier,
    onActionChanged: (Signs) -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.SpaceEvenly) {
        FourButtonRow(
            firstButtonValue = Signs.ClearAll,
            secondButtonValue = Signs.NotImplementedYet,
            thirdButtonValue = Signs.NotImplementedYet,
            fourthButtonValue = Signs.Divide,
            onFirstButtonChange = { action ->
                onActionChanged(action)
            },
            onSecondButtonChange = { action ->
                onActionChanged(action)
            },
            onThirdButtonChange = { action ->
                onActionChanged(action)
            },
            onFourthButtonChange = { action ->
                onActionChanged(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.Seven,
            secondButtonValue = Signs.Eight,
            thirdButtonValue = Signs.Nine,
            fourthButtonValue = Signs.Multiply,
            onFirstButtonChange = { action ->
                onActionChanged(action)
            },
            onSecondButtonChange = { action ->
                onActionChanged(action)
            },
            onThirdButtonChange = { action ->
                onActionChanged(action)
            },
            onFourthButtonChange = { action ->
                onActionChanged(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.Four,
            secondButtonValue = Signs.Five,
            thirdButtonValue = Signs.Six,
            fourthButtonValue = Signs.Minus,
            onFirstButtonChange = { action ->
                onActionChanged(action)
            },
            onSecondButtonChange = { action ->
                onActionChanged(action)
            },
            onThirdButtonChange = { action ->
                onActionChanged(action)
            },
            onFourthButtonChange = { action ->
                onActionChanged(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.One,
            secondButtonValue = Signs.Two,
            thirdButtonValue = Signs.Three,
            fourthButtonValue = Signs.Plus,
            onFirstButtonChange = { action ->
                onActionChanged(action)
            },
            onSecondButtonChange = { action ->
                onActionChanged(action)
            },
            onThirdButtonChange = { action ->
                onActionChanged(action)
            },
            onFourthButtonChange = { action ->
                onActionChanged(action)
            },
        )
        FourButtonRow(
            firstButtonValue = Signs.NotImplementedYet,
            secondButtonValue = Signs.Zero,
            thirdButtonValue = Signs.NotImplementedYet,
            fourthButtonValue = Signs.Sum,
            onFirstButtonChange = { action ->
                onActionChanged(action)
            },
            onSecondButtonChange = { action ->
                onActionChanged(action)
            },
            onThirdButtonChange = { action ->
                onActionChanged(action)
            },
            onFourthButtonChange = { action ->
                onActionChanged(action)
            },
        )
    }
}