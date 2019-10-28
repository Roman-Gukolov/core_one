package com.epam.unitTesting;

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test for calculator
 */
@Test
public class CalculatorTest {

    private Calculator calc = new Calculator();

    private int[] fibonacciData = new int[] {1, 1, 2, 3, 5, 8, 13, 21, 34, 55};

    @Test(groups = { "core"})
    public void testAddition() {
        Assert.assertEquals(calc.add(33d, 2d), calc.add(2d, 33d), 0);
        Assert.assertEquals(calc.add(33d, 2d) + 40,2 + calc.add(40d, 33d), 0);
        Assert.assertEquals((33 * 10) + (22 * 10),calc.add(22d, 33d) * 10, 0);
        Assert.assertEquals(-35, calc.add(-2d, -33d), 0);
    }

    @Test(groups = { "core"})
    public void testSubtraction() {
        Assert.assertEquals(calc.subtract(23d, 30d),0 - calc.subtract(30d, 23d), 0);
        Assert.assertNotEquals(calc.subtract(23d, 30d) - 100, 23 - calc.subtract(30d, 100d), 0);
        Assert.assertEquals((23 * 20) - (30 * 20), 20 * calc.subtract(23d, 30d), 0);
        Assert.assertEquals(20, calc.subtract(20d, 0d), 0);
        Assert.assertEquals(-20, calc.subtract(20d, 40d), 0);
    }

    @Test(groups = { "core"})
    public void testMultiplication() {
        Assert.assertEquals(calc.multiply(5d, 10d), calc.multiply(10d, 5d), 0);
        Assert.assertEquals(calc.multiply(5d, 10d) * 50, 5 * calc.multiply(10d, 50d), 0);
        Assert.assertEquals(calc.multiply(5d, 20d + 10d),  calc.multiply(5d, 20d) + calc.multiply(5d, 10d), 0);
        Assert.assertEquals(5,  calc.multiply(5d, 1d) * 1 * 1, 0);
        Assert.assertEquals(-5,  calc.multiply(5d, -1d), 0);
        Assert.assertEquals(0,  calc.multiply(5d, 0d), 0);
    }

    @Test(groups = { "core"})
    public void testDivision() {
        Assert.assertNotEquals(calc.division(10d, 2d), calc.division(2d, 10d), 0);
        Assert.assertNotEquals(calc.division(10d, 2d) / 2, 10 / calc.division(2d, 2d), 0);
        Assert.assertEquals(calc.division(10d + 12d, 2d), calc.division(10d, 2d) + calc.division(12d, 2d), 0);
        Assert.assertEquals(10, calc.division(10d, 1d), 0);
        Assert.assertEquals(0.5, calc.division(1d, 2d), 0);
        Assert.assertEquals(-1, calc.division(2d, -2d), 0);
        Assert.assertEquals(0, calc.division(0d, -2d), 0);
    }

    @Test(groups = { "core"})
    public void testRoot() {
        Assert.assertEquals(4, calc.root(16d), 0);
        Assert.assertEquals(4 * 2, calc.root(16d * 4d), 0);
        Assert.assertEquals(4 / 2, calc.root(16d / 4d), 0);

        double value = -3;
        Assert.assertEquals(Math.abs(value), calc.root(Math.pow(value, 2d)), 0);

    }

    @Test(groups = { "core"})
    public void testPower() {
        Assert.assertEquals(calc.pow(5d * 3d, 2d), calc.pow(5d, 2d) * calc.pow(3d, 2d), 0);
        Assert.assertEquals(calc.pow(5d / 3d, 2d), calc.pow(5d, 2d) / calc.pow(3d, 2d), 0.000000000001);
        Assert.assertEquals(calc.pow(5d,  2d) * calc.pow(5d, 4d), calc.pow(5d, 2d + 4d), 0);
        Assert.assertEquals(calc.pow(5d, 2d) / calc.pow(5d, 3d), calc.pow(5d, 2d - 3d), 0.000000000001);
        Assert.assertEquals(calc.pow(calc.pow(5d,  2d), 3d), calc.pow(5d, 2d * 3d), 0);

        Assert.assertEquals(25, calc.pow(-5d, 2d), 0);
        Assert.assertEquals(0.04, calc.pow(5d, -2d), 0);
        Assert.assertEquals(0, calc.pow(0d, 5d), 0);
    }


    @Test(groups = { "core"})
    public void testWithException() {
        org.testng.Assert.assertThrows(IllegalArgumentException.class, () -> calc.root(-4d));
        org.testng.Assert.assertThrows(IllegalArgumentException.class, () -> calc.division(10d,0d));
        org.testng.Assert.assertThrows(IllegalArgumentException.class, () -> calc.isPrime(-2));
    }

    @Test(groups = { "core"})
    public void testIsPrime() {
        Assert.assertTrue(calc.isPrime(2));
        Assert.assertTrue(calc.isPrime(11));
        Assert.assertTrue(calc.isPrime(29));

        Assert.assertFalse(calc.isPrime(15));
    }

    @Test(groups = { "core"})
    public void testFib() {
        assertThat(fibonacciData, equalTo(calc.fib(10)));
    }

}
