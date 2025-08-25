package com.example.calculaltorproject.domain.ext

import androidx.compose.foundation.layout.size
import com.example.calculaltorproject.domain.model.Actions
import com.example.calculaltorproject.domain.model.InputErrors
import com.example.calculaltorproject.domain.model.InputSignException
import com.example.calculaltorproject.domain.model.SignException
import com.example.calculaltorproject.domain.model.Signs
import com.example.calculaltorproject.domain.model.UnsupportedSignException
import com.example.calculaltorproject.framework.displayBuilder

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
            is Actions.Number -> action.number
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