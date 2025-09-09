package com.example.calculaltorproject.domain.model

import com.example.calculaltorproject.domain.model.Signs.Brackets.toSign
import org.junit.Assert.*
import org.junit.Test

class SignsTest {

    @Test
    fun givenNumbersFrom0to9_whenConvertedToSigns_thenConvertedToRightSigns() {
        //given                         //when
        val numbers = (0..9)

        //when
        val signs = numbers.map { toSign(it.toDouble()) }

        //then
        assertEquals(signs[0], Signs.Zero)
        assertEquals(signs[1], Signs.One)
        assertEquals(signs[2], Signs.Two)
        assertEquals(signs[3], Signs.Three)
        assertEquals(signs[4], Signs.Four)
        assertEquals(signs[5], Signs.Five)
        assertEquals(signs[6], Signs.Six)
        assertEquals(signs[7], Signs.Seven)
        assertEquals(signs[8], Signs.Eight)
        assertEquals(signs[9], Signs.Nine)
    }

    @Test
    fun givenNumbersMoreThan9_whenConvertedToSigns_thenConvertedToNumberHelper() {
        //given                         //when
        val number = 11.toDouble()

        //when
        val signs = toSign(number)

        //then
        assert(signs is Signs.NumberHelper)
        assert((signs as Signs.NumberHelper).helperNumber == number)
    }

}