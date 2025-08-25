package com.example.calculaltorproject.framework

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
import com.example.calculaltorproject.domain.ext.signComposer
import com.example.calculaltorproject.domain.ext.textComposer
import com.example.calculaltorproject.domain.ext.toVisualRepresentation
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.screen.displayer.Displayer
import com.example.calculaltorproject.presentation.screen.interactor.Interactor
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import kotlinx.coroutines.launch


val displayBuilder = StringBuilder()
val signsInUse = mutableListOf<Signs>()

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
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(5f),
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