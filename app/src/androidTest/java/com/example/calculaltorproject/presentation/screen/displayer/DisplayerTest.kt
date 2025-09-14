package com.example.calculaltorproject.presentation.screen.displayer

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.calculaltorproject.Ui
import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.theme.CalculaltorProjectTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(Ui::class)
@LargeTest
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

    @Test
    fun givenInteractor_whenDeleteSignIsPressed_thenActionOnDeleteClickedIsExecuted() =
        with(composeTestRule) {

            val delete = Signs.Delete
            val value = (delete.action as Actions.Sign).sign
            var onDeleteClicked = false

            //given
            setContent {
                Displayer(
                    signsValue = "",
                    previewResultValue = "",
                    onDeleteClicked = {
                        onDeleteClicked = !onDeleteClicked
                    }
                )
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertTrue(onDeleteClicked)

            return@with
        }

}