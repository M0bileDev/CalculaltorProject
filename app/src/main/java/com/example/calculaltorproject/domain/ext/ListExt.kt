package com.example.calculaltorproject.domain.ext

import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.InputErrors
import com.example.calculaltorproject.domain.model.InputSignException
import com.example.calculaltorproject.domain.model.SignException
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.domain.model.Signs.Brackets.toSign
import com.example.calculaltorproject.domain.model.UnsupportedSignException

typealias FirstValueIndex = Int
typealias SecondValueIndex = Int
typealias SignIndex = Int

val displayBuilder = StringBuilder()

fun List<Signs>.unsupportedLastSignsDetected(): Boolean {
    val unsupportedSigns = listOf(Signs.Plus, Signs.Minus, Signs.Multiply, Signs.Divide)
    val signDetected = unsupportedSigns.any { it == last() }
    return signDetected
}

fun List<Signs>.moreThanFifteenSameDigits(newSign: Signs): Boolean {
    val lastFifteenDigits = takeLast(15)
    val reversedLastFifteenDigits = lastFifteenDigits.reversed()
    return reversedLastFifteenDigits.groupBy { it }.any { it.key == newSign && it.value.size == 15 }
}

fun List<Signs>.textComposer(onTextCompleted: (String) -> Unit) {
    forEach { sign ->
        val value = when (val action = sign.action) {
            is Actions.Number -> action.numberSign
            is Actions.Sign -> action.sign
        }
        displayBuilder.append(value)
    }

    onTextCompleted(displayBuilder.toString())
    displayBuilder.clear()
}

fun MutableList<Signs>.signComposer(
    sign: Signs,
    onSignCompleted: (List<Signs>) -> Unit,
    onSignError: (SignException) -> Unit,
    onError: (InputErrors) -> Unit
) {

    try {
        if (sign == Signs.Sum) throw UnsupportedSignException()

        //perform action based on sign delete "C"
        if (sign == Signs.Delete) {
            if (isEmpty()) {
                throw InputSignException(InputErrors.NothingToDelete)
            }

            this.removeAt(this.size - 1)
            return
        }

        //perform action based on sign delete "AC"
        if (sign == Signs.ClearAll) {
            if (isEmpty()) {
                throw InputSignException(InputErrors.NothingToClear)
            }
            clear()
            return
        }

        //convert sign to action and perform text computation
        when (sign.action) {
            is Actions.Number -> {

                if (moreThanFifteenSameDigits(sign)) {
                    throw InputSignException(InputErrors.MoreThanFifteenSameDigits)
                }

                add(sign)
            }

            is Actions.Sign -> {

                if (isEmpty()) {
                    throw InputSignException(InputErrors.NoValueOnWhichCalculationsCanBeMade)
                }

                if (last() == sign) {
                    throw InputSignException(InputErrors.SignAlreadyUsed)
                }

                if (unsupportedLastSignsDetected()) {
                    throw InputSignException(InputErrors.UnsupportedLastSign)
                }

                add(sign)
            }
        }

    } catch (_: UnsupportedSignException) {
        onSignError(SignException.ExplicitSignException)
    } catch (e: InputSignException) {
        onError(e.inputError)
    } finally {
        onSignCompleted(this)
    }
}

fun MutableList<Signs>.groupNumbers() {

    //given list [Five,Five,Five,Plus,Four,Minus,Two,One,Zero]
    val returnList = mutableListOf<List<Signs>>()
    val helperList = mutableListOf<Signs>()
    this.forEachIndexed { index, item ->
        if (item.action !is Actions.Sign) {
            helperList.add(item)
            if (index == this.size - 1) {
                returnList.add(helperList.take(helperList.size))
            }
        } else {
            if (helperList.isNotEmpty()) {
                returnList.add(helperList.take(helperList.size))
                helperList.clear()
            }
            returnList.add(listOf(item))
        }
    }

    //given list [[Five,Five,Five],[Plus],[Four],[Minus],[Two,One,Zero]]
    val groupedNumbersSignsList = returnList.map {
        if (it.first().action is Actions.Number) {
            val builder = StringBuilder()
            it.map { sign -> builder.append((sign.action as Actions.Number).numberSign) }
            val numberSigns = builder.toString()
            val value = numberSigns.toLong()
            Signs.NumberHelper(numberSigns, value)
        } else {
            it.first()
        }
    }


    this.clear()

    //given list, sample converted to digits for simplicity [[555],[+],[4],[-],[210]]
    this.addAll(groupedNumbersSignsList)
}

fun precedenceStartValue() = Signs.NotImplementedYet to Triple(-1, -1, -1)

fun MutableList<Signs>.calculateValue(): Long {
    var theLowestPrecedence: Pair<Signs, Triple<SignIndex, FirstValueIndex, SecondValueIndex>> =
        precedenceStartValue()


    var signIsPresent = true
    while (signIsPresent) {

        forEachIndexed { index, sign ->

            //skip if sign is a number
            if (sign.precedence == -1) return@forEachIndexed

            //skip if sign is the same operator
            if (sign.precedence == theLowestPrecedence.first.precedence) return@forEachIndexed

            //skip if precedence order is higher than actual and actual is not the first sign (default is -1, not found earlier)
            if (sign.precedence > theLowestPrecedence.first.precedence && theLowestPrecedence.first.precedence != -1) return@forEachIndexed

            theLowestPrecedence = sign to Triple(index, index - 1, index + 1)
        }

        val firstNumber = (this[theLowestPrecedence.second.second].action as Actions.Number).number
        val secondNumber = (this[theLowestPrecedence.second.third].action as Actions.Number).number

        val result = when (theLowestPrecedence.first) {
            Signs.Divide -> {
                firstNumber / secondNumber
            }

            Signs.Multiply -> {
                firstNumber * secondNumber
            }

            Signs.Minus -> {
                firstNumber - secondNumber
            }

            Signs.Plus -> {
                firstNumber + secondNumber
            }

            else -> throw IllegalStateException("sign not allowed ${theLowestPrecedence.first}")
        }

        this.removeAt(theLowestPrecedence.second.second)
        // index changed drop one down
        this.removeAt(theLowestPrecedence.second.third - 1)
        // index changed drop one down
        this[theLowestPrecedence.second.first - 1] = toSign(result)

        signIsPresent = this.any { it.action is Actions.Sign }
        theLowestPrecedence = precedenceStartValue()
    }

    return (this.first().action as Actions.Number).number
}