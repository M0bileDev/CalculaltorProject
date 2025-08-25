package com.example.calculaltorproject.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculaltorproject.domain.ext.toVisualRepresentation
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.framework.RoundButton
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme

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