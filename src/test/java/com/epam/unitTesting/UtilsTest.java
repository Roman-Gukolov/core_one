package com.epam.unitTesting;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class UtilsTest {
    private Utils utils;

    @Before
    public void init() {
        utils = new Utils();
    }

    @Test
    public void testConcatenateWords() {
        String firstLine = "str1 ";
        String secondLine = "+ str\"2\"";
        assertEquals("str1 + str\"2\"", utils.concatenateWords(firstLine, secondLine));
    }

    @Test
    public void testComputeFactorial() {
        Assert.assertEquals(40320, utils.computeFactorial(8));
    }

    @Test(timeout = 1000)
    public void testFactorialWithTimeout() {
        Assert.assertEquals(40320, utils.computeFactorial(8));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExpectedException() {
       utils.computeFactorial(-5);
    }

    @Test
    @Ignore
    public void testNormalizeWord() {
        Assert.assertEquals("Schön", utils.normalizeWord("Schön"));
    }

}
