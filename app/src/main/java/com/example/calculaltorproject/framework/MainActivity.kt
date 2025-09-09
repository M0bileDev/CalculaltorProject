package com.example.calculaltorproject.framework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.calculaltorproject.domain.ext.groupNumbers
import com.example.calculaltorproject.domain.ext.previewComposer
import com.example.calculaltorproject.domain.ext.signComposer
import com.example.calculaltorproject.domain.ext.textComposer
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.screen.displayer.Displayer
import com.example.calculaltorproject.presentation.screen.interactor.Interactor
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import kotlinx.coroutines.launch


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
            var previewResult by remember { mutableStateOf("") }
            val coroutine = rememberCoroutineScope()

            Column(modifier = Modifier.padding(innerPadding)) {
                Displayer(
                    modifier = Modifier.weight(3f),
                    signsValue = displayValue,
                    previewResultValue = previewResult,
                    onDeleteClicked = {
                        signsInUse.signComposer(
                            Signs.Delete,
                            onSignCompleted = {
                                it.textComposer(onTextCompleted = { newText ->
                                    displayValue = newText
                                })

                                it.toMutableList()
                                    .previewComposer(onPreviewCompleted = { newPreview ->
                                        previewResult = newPreview
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

                                        //display numbers and signs
                                        it.textComposer(onTextCompleted = { newText ->
                                            displayValue = newText
                                        })

                                        //display calculated value in the preview, below numbers and signs
                                        it.toMutableList()
                                            .previewComposer(onPreviewCompleted = { newPreview ->
                                                previewResult = newPreview
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