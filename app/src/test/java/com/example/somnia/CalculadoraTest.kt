package com.example.somnia

import com.example.somnia.model.Calculator
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CalculadoraTest {
       @Test
    fun calculateTimeToGoBedTest() {
        var calculator_test: Calculator = Calculator("8:00", "8:00", "")
        assertEquals("0:0", calculator_test.calculateTimeToGoBed())
    }

    @Test
    fun calculateTimeToWakeUpTest(){
        var calculator_test: Calculator = Calculator("", "8:00", "23:00")
        assertEquals("7:0", calculator_test.calculateTimeToWakeUp())
    }
}

