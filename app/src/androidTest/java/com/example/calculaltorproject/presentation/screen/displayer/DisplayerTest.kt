package com.example.calculaltorproject.presentation.screen.displayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.framework.MainActivityViewModel
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class DisplayerTest {

    lateinit var viewModel: MainActivityViewModel

    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setup() {
        viewModel = MainActivityViewModel()
    }

    @Test
    fun test() {

        viewModel.onActionChanged(Signs.One)

        composeTestRule.setContent {
            CalculaltorProjectTheme {

                val signsValue by viewModel.display.collectAsStateWithLifecycle()
                val previewResultValue by remember { mutableStateOf("") }

                Displayer(
                    signsValue = signsValue,
                    previewResultValue = previewResultValue,
                    onDeleteClicked = {}
                )
            }
        }

        composeTestRule.onNodeWithTag("displayerTextField")
            .assertTextContains("1")
    }

}