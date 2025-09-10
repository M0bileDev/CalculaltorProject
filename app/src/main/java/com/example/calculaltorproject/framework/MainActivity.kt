package com.example.calculaltorproject.framework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculaltorproject.domain.ext.previewComposer
import com.example.calculaltorproject.domain.ext.signComposer
import com.example.calculaltorproject.domain.ext.textComposer
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.screen.displayer.Displayer
import com.example.calculaltorproject.presentation.screen.interactor.Interactor
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import kotlinx.coroutines.launch


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
fun CalculatorApp(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel = viewModel()
) = with(viewModel) {
    CalculaltorProjectTheme {
        val snackbarHostState = remember { SnackbarHostState() }

        val display by display.collectAsStateWithLifecycle()
        val preview by preview.collectAsStateWithLifecycle()

        Scaffold(
            modifier = modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { innerPadding ->

            Column(modifier = Modifier.padding(innerPadding)) {
                Displayer(
                    modifier = Modifier.weight(3f),
                    signsValue = display,
                    previewResultValue = preview,
                    onDeleteClicked = ::onDeleteClicked
                )
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))
                Interactor(
                    modifier = Modifier
                        .padding(16.dp)
                        .weight(5f),
                    onActionChanged = ::onActionChanged
                )
            }
        }
    }
}