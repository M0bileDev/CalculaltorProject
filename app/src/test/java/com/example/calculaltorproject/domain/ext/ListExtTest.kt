package com.example.calculaltorproject.domain.ext

import com.example.calculaltorproject.domain.model.InputErrors
import com.example.calculaltorproject.domain.model.SignException
import com.example.calculaltorproject.domain.model.Signs
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

const val delta = 1e-9

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ListExtTest {

    @Test
    fun test001_givenSignsList2_whenAdditionSignIsPresent_thenResultIsAddition() {

        //given list of signs
        val values = mutableListOf(Signs.Nine, Signs.Plus, Signs.Three)

        //when
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then
        assertEquals(result, 12.toDouble(), delta)
    }

    @Test
    fun test002_givenSignsList4_whenAdditionSignIsPresent_thenResultIsAddition() {

        //given list of signs
        val values = mutableListOf(Signs.Nine, Signs.Plus, Signs.Three, Signs.Plus, Signs.Four)

        //when
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then
        assertEquals(result, 16.toDouble(), delta)
    }

    @Test
    fun test003_givenSignsList2_whenMinusSignIsPresent_thenResultIsSubtraction() {

        //given list of two signs
        val values = mutableListOf(Signs.Zero, Signs.Minus, Signs.One)

        //when
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then
        assertEquals(result, (-1).toDouble(), delta)
    }

    @Test
    fun test004_givenSignsList4_whenMinusSignIsPresent_thenResultIsSubtraction() {

        //given list of signs
        val values = mutableListOf(Signs.Zero, Signs.Minus, Signs.One, Signs.Minus, Signs.Five)

        //when
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then
        assertEquals(result, (-6).toDouble(), delta)
    }

    @Test
    fun test005_givenSignsList4_whenPlusSignIsPresent_thenResultIsAddition() {

        //given list of signs,
        val values = mutableListOf(Signs.Five, Signs.Five, Signs.Plus, Signs.Five)

        //when
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then
        assertEquals(60.toDouble(), result, delta)
    }

    @Test
    fun test006_givenSignsList_whenLastSignIsDigit_thenUnsupportedLastSignsDetectedIsTrue() {
        //given list of signs,
        val values = mutableListOf(Signs.Five, Signs.Plus)

        //when
        val result = values.unsupportedLastSignsDetected()

        //then
        assertEquals(true, result)
    }

    @Test
    fun test006_givenSignsList_whenLastSignIsDigit_thenUnsupportedLastSignsDetectedIsFalse() {
        //given list of signs,
        val values = mutableListOf(Signs.Five, Signs.Plus, Signs.One)

        //when
        val result = values.unsupportedLastSignsDetected()

        //then
        assertEquals(false, result)
    }

    @Test
    fun test007_givenSignsList_whenSameDigitsSizeIs14_thenMoreThanFifteenSameDigitsIsFalse() {
        //given list of signs,
        val values = mutableListOf<Signs>()
        repeat(14) {
            values.add(Signs.One)
        }

        //when
        val result = values.moreThanFifteenSameDigits(Signs.One)

        //then
        assertEquals(false, result)
    }

    @Test
    fun test008_givenSignsList_whenSameDigitsSizeIs15_thenMoreThanFifteenSameDigitsIsTrue() {
        //given list of signs,
        val values = mutableListOf<Signs>()
        repeat(15) {
            values.add(Signs.One)
        }

        //when
        val result = values.moreThanFifteenSameDigits(Signs.One)

        //then
        assertEquals(true, result)
    }

    @Test
    fun test009_givenSignsList_whenTextComposed_thenCorrectStringIsCreated() {
        //given numberSign
        val helperSign = "210"
        //given list of signs
        val values =
            mutableListOf(Signs.NumberHelper(helperSign, helperSign.toDouble()), Signs.Plus)

        //when
        var result = ""
        values.textComposer { result = it }

        //then
        assertEquals("$helperSign+", result)
    }

    @Test
    fun test010_givenSignList_whenSignsPassedIsSum_thenSignComposerExecuteOnSignError() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.One, Signs.Zero)
        lateinit var exception: SignException


        //when, clicked sign is "sum"
        values.signComposer(
            sign = Signs.Sum,
            onSignCompleted = {},
            onSignError = {
                exception = it
            },
            onError = {}
        )

        //then, exception is not null
        assertNotNull(exception)
    }

    @Test
    fun test011_givenSignList_whenSignsPassedIsPlus_thenOnSignCompletedEndsWithThisSign() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.One, Signs.Zero)
        //given plus sign
        val plusSign = Signs.Plus

        //when, clicked sign is "plus"
        values.signComposer(
            sign = plusSign,
            onSignCompleted = {},
            onSignError = {},
            onError = {}
        )

        //then, last element of list is equal to Plus
        assertSame(plusSign, values.last())
    }

    @Test
    fun test012_givenSignList_whenSignsPassedIsDelete_thenSignsListIsReduced() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.One, Signs.Zero)


        //when, clicked sign is "delete"
        values.signComposer(
            sign = Signs.Delete,
            onSignCompleted = {},
            onSignError = {},
            onError = {}
        )

        //then, signs list size must be reduced
        assertEquals(1, values.size)
    }

    @Test
    fun test013_givenEmptySignList_whenSignsPassedIsDelete_thenInputErrorNothingToDeleteIsThrown() {

        //given empty list of signs
        val values: MutableList<Signs> = mutableListOf()
        //exception
        lateinit var inputError: InputErrors


        //when, clicked sign is "delete"
        values.signComposer(
            sign = Signs.Delete,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                inputError = it
            }
        )

        //then, error is NothingToDelete
        assertEquals(inputError, InputErrors.NothingToDelete)
    }

    @Test
    fun test014_givenSignList_whenSignsPassedIsClearAll_thenListSizeIsZero() {

        //given empty list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.One, Signs.Zero)

        //when, clicked sign is "delete"
        values.signComposer(
            sign = Signs.ClearAll,
            onSignCompleted = {},
            onSignError = {},
            onError = {}
        )

        //then, signs list size must be 0
        assertEquals(0, values.size)
    }

    @Test
    fun test015_givenEmptySignList_whenSignsPassedIsClearAll_thenInputErrorNothingToClearIsThrown() {

        //given empty list of signs
        val values: MutableList<Signs> = mutableListOf()
        //given input error
        lateinit var error: InputErrors

        //when, clicked sign is "delete"
        values.signComposer(
            sign = Signs.ClearAll,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                error = it
            }
        )

        //then, inout error is NothingToClear
        assertEquals(error, InputErrors.NothingToClear)
    }

    @Test
    fun test016_givenSignList_whenSignsPassedIsNumber_thenSignListIsGreater() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.Zero)

        //when, clicked sign is "Zero"
        values.signComposer(
            sign = Signs.Zero,
            onSignCompleted = {},
            onSignError = {},
            onError = {}
        )

        //then, signs list size must be reduced
        assertEquals(2, values.size)
    }

    @Test
    fun test017_givenSignList15SameDigits_whenSignsPassedIsSameDigit_thenMoreThanFifteenSameDigitsErrorIsThrown() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf()
        repeat(15) {
            values.add(Signs.Zero)
        }
        //given error
        lateinit var error: InputErrors


        //when, clicked sign is "Zero"
        values.signComposer(
            sign = Signs.Zero,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                error = it
            }
        )

        //then, error MoreThanFifteenSameDigits is thrown
        assertEquals(error, InputErrors.MoreThanFifteenSameDigits)
    }

    @Test
    fun test017_givenEmptySignList_whenSignsActionIsSign_thenNoValueOnWhichCalculationsCanBeMadeErrorIsThrown() {

        //given empty list of signs
        val values: MutableList<Signs> = mutableListOf()
        //given error
        lateinit var error: InputErrors


        //when, clicked sign is "plus"
        values.signComposer(
            sign = Signs.Plus,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                error = it
            }
        )

        //then, error NoValueOnWhichCalculationsCanBeMade is thrown
        assertEquals(error, InputErrors.NoValueOnWhichCalculationsCanBeMade)
    }

    @Test
    fun test018_givenSignList_whenLastSignIsSameActionSign_thenSignAlreadyUsedErrorIsThrown() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.Zero, Signs.Plus)
        //given error
        lateinit var error: InputErrors


        //when, clicked sign is "plus"
        values.signComposer(
            sign = Signs.Plus,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                error = it
            }
        )

        //then, error NoValueOnWhichCalculationsCanBeMade is thrown
        assertEquals(error, InputErrors.SignAlreadyUsed)
    }

    @Test
    fun test019_givenSignList_whenLastSignIsActionSign_thenUnsupportedLastSignErrorIsThrown() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.Zero, Signs.Plus)
        //given error
        lateinit var error: InputErrors


        //when, clicked sign is "minus"
        values.signComposer(
            sign = Signs.Minus,
            onSignCompleted = {},
            onSignError = {},
            onError = {
                error = it
            }
        )

        //then, error NoValueOnWhichCalculationsCanBeMade is thrown
        assertEquals(error, InputErrors.UnsupportedLastSign)
    }

    @Test
    fun test020_givenSignList_whenLastSignIsPassed_thenLastSignIsPresent() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.Zero)
        //given sign
        val sign = Signs.Plus


        //when, clicked sign is "plus"
        values.signComposer(
            sign = Signs.Plus,
            onSignCompleted = {},
            onSignError = {},
            onError = {}
        )

        //then, error NoValueOnWhichCalculationsCanBeMade is thrown
        assertEquals(sign, values.last())
    }

    @Test
    fun test021_givenSignList_whenSignListContainsDivideAndMultiply_thenResultIsCorrectByPreservePrecedence() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(
            Signs.Two, Signs.Multiply, Signs.Three,
            Signs.Divide, Signs.Two
        )

        //when, value is calculated
        val result = values.run {
            groupNumbers()
            calculateValue()
        }

        //then, sign precedence is preserved
        assertEquals(result, 3.toDouble(), delta)
    }

    @Test
    fun test022_givenSignList_whenSignListContainsNotAllowedSign_thenIllegalStateExceptionIsThrown() {

        //given list of signs
        val values: MutableList<Signs> = mutableListOf(Signs.Two, Signs.Percentage, Signs.Three)

        //when, value is calculated, then, sign precedence is preserved
        assertThrows(IllegalStateException::class.java, ({
            values.calculateValue()
        }))
    }

    @Test
    fun test023_givenSignListLastElementSign_whenRemoveLastSign_thenLastListElementIsNotSign() {
        //given
        val values: MutableList<Signs> =
            mutableListOf(Signs.Two, Signs.Percentage, Signs.Three, Signs.Multiply)

        //when
        values.removeLastSign()

        //then, result sign list last element is not a Sign
        assertEquals(values.last(), Signs.Three)
    }

    @Test
    fun test024_givenSignListLastElementNumber_whenRemoveLastSign_thenListPreserveTheSame() {
        //given
        val values: MutableList<Signs> = mutableListOf(Signs.Two, Signs.Percentage, Signs.Three)

        //when
        values.removeLastSign()

        //then, result sign list last element is not a Sign
        assertEquals(values.last(), Signs.Three)
    }

    @Test
    fun test025_givenRightSignList_whenPreviewComposer_thenPreviewSuccessful() {
        //given
        val values = mutableListOf(Signs.Four, Signs.Divide, Signs.Two)
        lateinit var preview: String

        //when
        values.previewComposer(onPreviewCompleted = {
            preview = it
        })

        //then
        assertEquals("2.0", preview)
    }

    @Test
    fun test026_givenSignListLastIsSign_whenPreviewComposer_thenPreviewSuccessfulEmpty() {
        //given
        val values = mutableListOf(Signs.Four, Signs.Divide, Signs.Two, Signs.Plus)
        lateinit var preview: String

        //when
        values.previewComposer(onPreviewCompleted = {
            preview = it
        })

        //then
        assertEquals("", preview)
    }

    @Test
    fun test027_givenSignList_whenGroupNumbers_thenNumbersAreGrouped() {
        //given
        val values =
            mutableListOf(Signs.One, Signs.Zero, Signs.Zero, Signs.Plus, Signs.Two, Signs.Zero)

        //when
        values.groupNumbers()

        //then
        assertEquals(values.size, 3)
    }
}