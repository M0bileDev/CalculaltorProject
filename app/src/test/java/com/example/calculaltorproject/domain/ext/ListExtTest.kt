package com.example.calculaltorproject.domain.ext

import com.example.calculaltorproject.domain.model.Signs
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ListExtTest {

    @Test
    fun test1_givenSignsList2_whenAdditionSignIsPresent_thenResultIsAddition() {

        //given list of signs
        val values = mutableListOf(Signs.Nine, Signs.Plus, Signs.Three)

        //when
        val result = values.calculateValue()

        //then
        assertEquals(result, 12)
    }

    @Test
    fun test2_givenSignsList4_whenAdditionSignIsPresent_thenResultIsAddition() {

        //given list of signs
        val values = mutableListOf(Signs.Nine, Signs.Plus, Signs.Three, Signs.Plus, Signs.Four)

        //when
        val result = values.calculateValue()

        //then
        assertEquals(result, 16)
    }

    @Test
    fun test3_givenSignsList2_whenMinusSignIsPresent_thenResultIsSubtraction() {

        //given list of two signs
        val values = mutableListOf(Signs.Zero, Signs.Minus, Signs.One)

        //when
        val result = values.calculateValue()

        //then 1
        assertEquals(result, -1)
    }

    @Test
    fun test4_givenSignsList4_whenMinusSignIsPresent_thenResultIsSubtraction() {

        //given list of signs
        val values = mutableListOf(Signs.Zero, Signs.Minus, Signs.One, Signs.Minus, Signs.Five)

        //when
        val result = values.calculateValue()

        //then 1
        assertEquals(result, -6)
    }

    @Test
    fun test5_givenSignsList4_whenPlusSignIsPresent_thenResultIsAddition() {

        //given list of signs
        val values = mutableListOf(Signs.Five, Signs.Five, Signs.Plus, Signs.Five)

        //when
        val result = values.calculateValue()

        //then 1
        assertEquals(result, 60)
    }

    // TODO: add new tests

}