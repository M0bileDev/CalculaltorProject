package com.example.calculaltorproject.presentation.screen.interactor

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.filters.LargeTest
import com.example.calculaltorproject.Ui
import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.Signs
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(Ui::class)
@LargeTest
class InteractorTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun givenInteractor_whenNumberZeroIsPressed_thenActionReturnSignsZero() =
        with(composeTestRule) {

            val zero = Signs.Zero
            val value = (zero.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, zero)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberOneIsPressed_thenActionReturnSignsOne() = with(composeTestRule) {

        val one = Signs.One
        val value = (one.action as Actions.Number).numberSign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, one)

        return@with
    }

    @Test
    fun givenInteractor_whenNumberTwoIsPressed_thenActionReturnSignsTwo() = with(composeTestRule) {

        val two = Signs.Two
        val value = (two.action as Actions.Number).numberSign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, two)

        return@with
    }

    @Test
    fun givenInteractor_whenNumberThreeIsPressed_thenActionReturnSignsThree() =
        with(composeTestRule) {

            val three = Signs.Three
            val value = (three.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, three)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberFourIsPressed_thenActionReturnSignsFour() =
        with(composeTestRule) {

            val four = Signs.Four
            val value = (four.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, four)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberFiveIsPressed_thenActionReturnSignsFive() =
        with(composeTestRule) {

            val five = Signs.Five
            val value = (five.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, five)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberSixIsPressed_thenActionReturnSignsSix() = with(composeTestRule) {

        val six = Signs.Six
        val value = (six.action as Actions.Number).numberSign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, six)

        return@with
    }

    @Test
    fun givenInteractor_whenNumberSevenIsPressed_thenActionReturnSignsSeven() =
        with(composeTestRule) {

            val seven = Signs.Seven
            val value = (seven.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, seven)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberEightIsPressed_thenActionReturnSignsEight() =
        with(composeTestRule) {

            val eight = Signs.Eight
            val value = (eight.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, eight)

            return@with
        }

    @Test
    fun givenInteractor_whenNumberNineIsPressed_thenActionReturnSignsNine() =
        with(composeTestRule) {

            val nine = Signs.Nine
            val value = (nine.action as Actions.Number).numberSign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, nine)

            return@with
        }

    @Test
    fun givenInteractor_whenPlusSignIsPressed_thenActionReturnPlusSign() = with(composeTestRule) {

        val plus = Signs.Plus
        val value = (plus.action as Actions.Sign).sign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, plus)

        return@with
    }

    @Test
    fun givenInteractor_whenMinusSignIsPressed_thenActionReturnMinusSign() = with(composeTestRule) {

        val minus = Signs.Minus
        val value = (minus.action as Actions.Sign).sign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, minus)

        return@with
    }

    @Test
    fun givenInteractor_whenMultiplySignIsPressed_thenActionReturnMultiplySign() =
        with(composeTestRule) {

            val multiply = Signs.Multiply
            val value = (multiply.action as Actions.Sign).sign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, multiply)

            return@with
        }

    @Test
    fun givenInteractor_whenDivideSignIsPressed_thenActionReturnDivideSign() =
        with(composeTestRule) {

            val divide = Signs.Divide
            val value = (divide.action as Actions.Sign).sign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, divide)

            return@with
        }

    @Test
    fun givenInteractor_whenClearAllSignIsPressed_thenActionReturnClearAllSign() =
        with(composeTestRule) {

            val clearAll = Signs.ClearAll
            val value = (clearAll.action as Actions.Sign).sign
            lateinit var sign: Signs

            //given
            setContent {
                Interactor {
                    sign = it
                }
            }

            //when
            onNodeWithText(value).assertExists().performClick()

            //then
            assertEquals(sign, clearAll)

            return@with
        }

    @Test
    fun givenInteractor_whenSumSignIsPressed_thenActionReturnSumSign() = with(composeTestRule) {

        val sum = Signs.Sum
        val value = (sum.action as Actions.Sign).sign
        lateinit var sign: Signs

        //given
        setContent {
            Interactor {
                sign = it
            }
        }

        //when
        onNodeWithText(value).assertExists().performClick()

        //then
        assertEquals(sign, sum)

        return@with
    }

}