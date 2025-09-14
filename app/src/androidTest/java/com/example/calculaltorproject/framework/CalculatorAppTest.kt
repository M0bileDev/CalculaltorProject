package com.example.calculaltorproject.framework

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.calculaltorproject.Integration
import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.presentation.screen.displayer.displayerSupportTextField
import com.example.calculaltorproject.presentation.screen.displayer.displayerTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(Integration::class)
@LargeTest
class CalculatorAppTest {

    lateinit var mainActivityViewModel: MainActivityViewModel

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        mainActivityViewModel = MainActivityViewModel()
    }

    @Test
    fun givenSigns_whenContentSetAndOnNodeClickPerformed_thenDisplayerTextFieldContainsRightSigns() =
        with(composeTestRule) {

            //given
            val one = Signs.One
            val valueOne = (one.action as Actions.Number).numberSign

            val plus = Signs.Plus
            val valuePlus = (plus.action as Actions.Sign).sign


            //when
            setContent {
                CalculatorApp(
                    viewModel = mainActivityViewModel
                )
            }

            onNodeWithText(valueOne).assertExists().performClick()
            onNodeWithText(valuePlus).assertExists().performClick()
            onNodeWithText(valueOne).assertExists().performClick()

            //then
            onNodeWithTag(displayerTextField)
                .assertTextContains("1+1")

            return@with
        }

    @Test
    fun givenSigns_whenContentSetAndOnNodeClickPerformed_thenPreviewContainsCorrectResult() =
        with(composeTestRule) {

            //given
            val one = Signs.One
            val valueOne = (one.action as Actions.Number).numberSign

            val plus = Signs.Plus
            val valuePlus = (plus.action as Actions.Sign).sign


            //when
            setContent {
                CalculatorApp(
                    viewModel = mainActivityViewModel
                )
            }

            onNodeWithText(valueOne).assertExists().performClick()
            onNodeWithText(valuePlus).assertExists().performClick()
            onNodeWithText(valueOne).assertExists().performClick()

            //then
            onNodeWithTag(displayerSupportTextField, useUnmergedTree = true)
                .assertTextContains("2.0")

            return@with
        }


}