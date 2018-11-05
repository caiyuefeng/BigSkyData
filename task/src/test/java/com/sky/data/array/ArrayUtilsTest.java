package com.sky.data.array;

import org.junit.Test;

/**
 * @Author: 蔡月峰
 * @Version： 1.0
 * @Description:
 * @Date : Create in 22:09 2018/11/5
 * @Modified By:
 */
public class ArrayUtilsTest {

    ArrayUtils utils = new ArrayUtils();

    @Test
    public void test() {
        ArrayUtils.medianNumber(new int[]{}, new int[]{});
        ArrayUtils.transformSawtooth("", 1);
    }
}