package com.example.calculaltorproject.domain.ext

import com.example.calculaltorproject.FastTest
import com.example.calculaltorproject.domain.model.Signs
import org.junit.Assert.*
import org.junit.Test
import org.junit.experimental.categories.Category

@Category(FastTest::class)
class SignsExtTest {

    @Test
    fun givenSigns_whenSignActionIsSign_thenResultIsStringSign(){
        //given
        val sign = Signs.Plus

        //when
        val result = sign.toVisualRepresentation()

        //then
        assertEquals(result, "+")
    }

    @Test
    fun givenSigns_whenSignActionIsNumber_thenResultIsStringNumber(){
        //given
        val sign = Signs.Zero

        //when
        val result = sign.toVisualRepresentation()

        //then
        assertEquals(result, "0")
    }

}