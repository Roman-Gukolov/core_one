package com.epam.unitTesting;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@org.testng.annotations.Test
public class UtilsTest {
    private Utils utils = new Utils();

    @org.testng.annotations.Test(groups = { "core"})
    public void testConcatenateWords() {
        String firstLine = "str1 ";
        String secondLine = "+ str\"2\"";
        assertThat("str1 + str\"2\"", equalTo(utils.concatenateWords(firstLine, secondLine)));
    }

    @org.testng.annotations.Test(groups = { "core"})
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
    public void testNormalizeWord() {
        Assert.assertEquals("Schön", utils.normalizeWord("Schön"));
    }

}
