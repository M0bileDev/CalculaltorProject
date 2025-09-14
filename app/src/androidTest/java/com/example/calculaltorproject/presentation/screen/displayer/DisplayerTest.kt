package com.example.calculaltorproject.presentation.screen.displayer

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import org.junit.Rule
import org.junit.Test

class DisplayerTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenSignsOne_whenSignsValueTakesNumberSign_thenDisplayerTextFieldContainsOne() =
        with(composeTestRule) {

            //given
            val signsValue = (Signs.One.action as Actions.Number).numberSign

            //when
            setContent {
                CalculaltorProjectTheme {

                    Displayer(
                        signsValue = signsValue,
                        previewResultValue = "",
                        onDeleteClicked = {}
                    )
                }
            }

            //then
            onNodeWithTag(displayerTextField)
                .assertTextContains(signsValue)

            return@with
        }

    @Test
    fun givenSignsOne_whenPreviewResultValueTakesNumberSign_thenDisplayerSupportTextFieldContainsOne() =
        with(composeTestRule) {

            //given
            val previewResultValue = (Signs.One.action as Actions.Number).numberSign

            //when
            setContent {
                CalculaltorProjectTheme {

                    Displayer(
                        signsValue = "",
                        previewResultValue = previewResultValue,
                        onDeleteClicked = {}
                    )
                }
            }
            //then
            onNodeWithTag(displayerSupportTextField, useUnmergedTree = true)
                .assertTextContains(previewResultValue)

            return@with

        }

}