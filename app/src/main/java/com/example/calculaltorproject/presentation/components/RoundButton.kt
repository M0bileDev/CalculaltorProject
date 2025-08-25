package com.example.calculaltorproject.presentation.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme

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