package com.sky.data.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 数组操作工具类
 * @date : 2018/10/8 8:59
 **/
public class ArrayUtils {

    /**
     * 寻找两个排序数组的中位数
     * 分治算法_应用
     *
     * @param first  第一个排序数组
     * @param second 第二个排序数组
     * @return 中位数
     */
    public static double medianNumber(int[] first, int[] second) {
        // 数组一的长度
        int m = first.length;
        // 数组二的长度
        int n = second.length;
        if (m > n) {
            return medianNumber(second, first);
        }
        double max;
        double min;
        // 数组一的左元素
        double lF = 0.0;
        // 数组一的右元素
        double lS = 0.0;
        // 数组二的左元素
        double rF = 0.0;
        // 数组二的右元素
        double rS = 0.0;
        // 数组一的"割"
        int fC;
        // 数组二的"割"
        int sC;
        // 数组一的虚拟数组低位
        int fL = 0;
        // 数组一的虚拟数组的高位
        int fH = 2 * m;
        while (fL <= fH) {
            // 数组一的"割"所在的位置
            fC = (fL + fH) / 2;
            // 数组二的"割"所在的位置
            sC = m + n - fC;
            // 数组一的左元素
            lF = fC == 0 ? Long.MIN_VALUE : first[(fC - 1) / 2];
            rF = fC == 2 * m ? Long.MAX_VALUE : first[fC / 2];
            lS = sC == 0 ? Long.MIN_VALUE : second[(sC - 1) / 2];
            rS = sC == 2 * n ? Long.MAX_VALUE : second[sC / 2];
            if (lF > rS) {
                fH = fC - 1;
            } else if (lS > rF) {
                fL = fC + 1;
            } else {
                break;
            }
        }
        min = lF < lS ? lS : lF;
        max = rF < rS ? rF : rS;
        return (min + max) / 2;
    }

    /**
     * 将输入字符串按照指定输入行数转换为"矩形"字符串输出
     * 如:
     * 输入: s = "PAYPALISHIRING", numRows = 4
     * 输出: "PINALSIGYAHRPI"
     * 解析:
     * P     I    N
     * A   L S  I G
     * Y A   H R
     * P     I
     *
     * @param inputStr 输入字符串
     * @param numRows  指定的矩形行数
     * @return 矩形化字符串
     */
    public static String transformSawtooth(String inputStr, int numRows) {
        if (numRows == 1) {
            return inputStr;
        }
        if (numRows == 0) {
            return "";
        }
        int currentCharIndex = 0;
        int currentRowIndex = 0;
        final StringBuilder builder = new StringBuilder();
        List<StringBuilder> storeArray = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            storeArray.add(new StringBuilder());
        }
        boolean pattern = false;
        int maxLength = inputStr.length();
        for (int charIndex = 0; charIndex < maxLength; charIndex++) {
            storeArray.get(currentRowIndex).append(inputStr.charAt(currentCharIndex++));
            if (currentRowIndex == numRows - 1) {
                pattern = true;
            } else if (currentRowIndex == 0) {
                pattern = false;
            }
            if (pattern) {
                currentRowIndex--;
                continue;
            }
            currentRowIndex++;
        }

        for (int i = 0; i < numRows; i++) {
            builder.append(storeArray.get(i).toString());
        }
        return builder.toString();
    }
}
