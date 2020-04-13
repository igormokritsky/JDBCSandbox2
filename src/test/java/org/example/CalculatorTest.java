package org.example;

import org.junit.*;

import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    public void testGetSum() {
        assertEquals(20, calculator.getSum(5,15));
    }

    @Test
    public void testGetDivide() {
        assertEquals(5,calculator.getDivide(15,3));
    }

    @Test
    public void testGetMultiple() {
        assertEquals(100, calculator.getMultiple(5,20));
    }
}