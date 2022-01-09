package com.sbrf.reboot.calculator;

import com.sbrf.reboot.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void getAddition() {
        assertEquals(9, new Calculator().getAddition(4, 5));
    }

    @Test
    void getSubtraction() {
        assertEquals(-1, new Calculator().getSubtraction(4, 5));
    }

    @Test
    void getMultiplication() {
        assertEquals(20, new Calculator().getMultiplication(4, 5));
    }

    @Test
    void getDivision() {
        assertEquals(3, new Calculator().getDivision(9, 3));
    }

    @Test
    void getDivisionByZero() {
        assertDoesNotThrow( () -> new Calculator().getDivision(1, 0));
    }

    @Test
    void getSinus() {
        assertEquals(0, new Calculator().getSinus(0));
    }

    @Test
    void getCosines() {
        assertEquals(1, new Calculator().getCosines(0));
    }

    @Test
    void getLogarithm() {
        assertEquals(0, new Calculator().getLogarithm(1));
    }

    @Test
    void classHasSevenMethods() {
        assertEquals(7, Calculator.class.getMethods().length - Object.class.getMethods().length);
    }
}