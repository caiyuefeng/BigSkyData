package com.sky.data.function;


import org.junit.Test;

import static org.junit.Assert.*;


public class CheckTimeFunctionTest {

    /**
     * 函数实例
     */
    private CheckTimeFunction checkTimeFunction = new CheckTimeFunction();

    @Test
    public void testStandard(){
        assertEquals("1",checkTimeFunction.evaluate("20180802201133"));
        assertEquals("0",checkTimeFunction.evaluate("201808022011331"));
        assertEquals("0",checkTimeFunction.evaluate("2018080220113"));
        assertEquals("1",checkTimeFunction.evaluate("19700101080000"));
        assertEquals("0",checkTimeFunction.evaluate("19700101070000"));
        assertEquals("0",checkTimeFunction.evaluate("19700101880000"));
    }

}
