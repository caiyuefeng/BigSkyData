package com.sky.data.array;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 数组操作工具类
 * @date : 2018/10/8 8:59
 **/
public class ArrayUtils {

    /**
     * 寻找两个排序数组的中位数
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
        double max = second[0];
        double min = second[second.length-1];
        // 数组一的左元素
        double L_F = 0.0;
        // 数组一的右元素
        double L_S = 0.0;
        // 数组二的左元素
        double R_F = 0.0;
        // 数组二的右元素
        double R_S = 0.0;
        // 数组一的"割"
        int F_C = 0;
        // 数组二的"割"
        int S_C = 0;
        // 数组一的虚拟数组低位
        int F_L = 0;
        // 数组一的虚拟数组的高位
        int F_H = 2 * m;
        while (F_L <= F_H) {
            // 数组一的"割"所在的位置
            F_C = (F_L + F_H) / 2;
            // 数组二的"割"所在的位置
            S_C = m + n - F_C;
            // 数组一的左元素
            L_F = F_C == 0 ? Long.MIN_VALUE : first[(F_C - 1) / 2];
            R_F = F_C == 2 * m ? Long.MAX_VALUE : first[F_C / 2];
            L_S = S_C == 0 ?  Long.MIN_VALUE : second[(S_C - 1) / 2];
            R_S = S_C == 2 * n ? Long.MAX_VALUE : second[S_C / 2];
            if (L_F > R_S) {
                F_H = F_C - 1;
            } else if (L_S > R_F) {
                F_L = F_C + 1;
            } else {
                break;
            }
        }
        min = L_F < L_S ? L_S : L_F;
        max = R_F < R_S ? R_F : R_S;
        return (min + max) / 2;
    }

    public static void main(String[] args) {
        System.out.println(medianNumber(new int[]{1,3},new int []{2}));
    }
}
