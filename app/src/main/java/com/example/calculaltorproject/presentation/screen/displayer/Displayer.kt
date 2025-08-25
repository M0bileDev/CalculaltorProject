package com.example.calculaltorproject.presentation.screen.displayer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculaltorproject.presentation.components.RoundButton

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