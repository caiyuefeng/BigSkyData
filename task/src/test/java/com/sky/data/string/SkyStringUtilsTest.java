package com.sky.data.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SkyStringUtilsTest {
    private SkyStringUtils skyStringUtils = new SkyStringUtils();

    @Test
    public void isPalindromeTest() {
        assertTrue(skyStringUtils.isPalindrome(121));
        assertFalse(skyStringUtils.isPalindrome(12));
        assertTrue(skyStringUtils.isPalindrome(0));
        assertTrue(skyStringUtils.isPalindrome(12321));
        assertFalse(skyStringUtils.isPalindrome(1232));
    }

    @Test
    public void minTest() {
        assertEquals(1, skyStringUtils.min(1, 2));
        assertEquals(0, skyStringUtils.min(0, 0));
        assertEquals(2, skyStringUtils.min(3, 2));
    }

    @Test
    public void maxAreaTest() {
        assertEquals(49, skyStringUtils.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(4, skyStringUtils.maxArea(new int[]{1, 2, 4, 3}));
        assertEquals(17, skyStringUtils.maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
    }

    @Test
    public void getRomanTest() {
        assertEquals("II", skyStringUtils.getRoman(2, new String[]{"I", "IV", "V", "IX"}));
        assertEquals("LX", skyStringUtils.getRoman(4, new String[]{"X", "XL", "L", "XC"}));
        assertEquals("DCCC", skyStringUtils.getRoman(8, new String[]{"C", "CD", "D", "CM"}));
    }

    @Test
    public void parseIntToRomanNumberTest() {
        assertEquals("II", skyStringUtils.parseIntToRomanNumber(2));
        assertEquals("XL", skyStringUtils.parseIntToRomanNumber(40));
        assertEquals("DCCC", skyStringUtils.parseIntToRomanNumber(800));
        assertEquals("MMMCMXCIX", skyStringUtils.parseIntToRomanNumber(3999));
    }

    @Test
    public void longestCommonPrefixTest() {
        assertEquals("fl", skyStringUtils.longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
        assertEquals("", skyStringUtils.longestCommonPrefix(new String[]{"dog", "racecar", "car"}));
        assertEquals("dog", skyStringUtils.longestCommonPrefix(new String[]{"dog"}));
        assertEquals("dog", skyStringUtils.longestCommonPrefix(new String[]{"dog", "dog", "dog"}));
        assertEquals("dog", skyStringUtils.longestCommonPrefix(new String[]{"dog", "dogee", "dogff"}));
        assertEquals("dog", skyStringUtils.longestCommonPrefix(new String[]{"dogfffff", "dogee", "dogff"}));
        assertEquals("", skyStringUtils.longestCommonPrefix(new String[]{""}));
        assertEquals("a", skyStringUtils.longestCommonPrefix(new String[]{"aa", "a"}));
    }

    @Test
    public void threeSumTest() {
//        for (List<Integer> array : skyStringUtils.threeSum(new int[]{-1, 0, 1, 2, -1, -4})) {
//            System.out.println(array);
//        }
    }

    @Test
    public void threeSumClosestTest() {
        assertEquals(2, skyStringUtils.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        assertEquals(2, skyStringUtils.threeSumClosest(new int[]{-1, 2, 1}, 0));
        assertEquals(0, skyStringUtils.threeSumClosest(new int[]{0, 2, 1, -3}, 1));
        assertEquals(-1, skyStringUtils.threeSumClosest(new int[]{1, 1, -1, -1, 3}, -1));
        assertEquals(82, skyStringUtils.threeSumClosest(new int[]{1, 2, 4, 8, 16, 32, 64, 128}, 82));
        assertEquals(2, skyStringUtils.threeSumClosest(new int[]{-1, 0, 1, 1, 55}, 3));

        assertEquals(17, skyStringUtils.threeSumClosest(new int[]{-10, 0, -2, 3, -8, 1, -10, 8, -8, 6, -7, 0, -7, 2, 2, -5, -8, 1, -4, 6}, 18));
    }

    @Test
    public void letterCombinationsTest() {
        String[] sts = new String[]{"ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"};
        List<String> result = new ArrayList<>();
        assertEquals(result, skyStringUtils.letterCombinations(""));
        for (String st : sts) {
            result.add(st);
        }
        assertEquals(result, skyStringUtils.letterCombinations("23"));
    }

    @Test
    public void isLongPressedName() {
        assertEquals(true, skyStringUtils.isLongPressedName("alex", "aaleex"));
        assertEquals(false, skyStringUtils.isLongPressedName("saeed", "ssaaedd"));
        assertEquals(true, skyStringUtils.isLongPressedName("leelee", "lleeelee"));
        assertEquals(true, skyStringUtils.isLongPressedName("laiden", "laiden"));
        assertEquals(false, skyStringUtils.isLongPressedName("pyplrz", "ppyypllr"));
    }


    @Test
    public void solveSudoku() {
        char[][] chars1 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        char[][] chars2 = new char[][]{
                {'5', '3', '4', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '3', '4', '8'},
                {'1', '9', '8', '3', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '7', '9', '1'},
                {'7', '1', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '3', '5'},
                {'3', '4', '5', '2', '8', '6', '1', '7', '9'}};
        skyStringUtils.solveSudoku(chars1);
        assertEquals(chars2, chars1);
    }

    @Test
    public void firstMissingPositive() {

        assertEquals(3, skyStringUtils.firstMissingPositive(new int[]{1, 2, 0}));
        assertEquals(2, skyStringUtils.firstMissingPositive(new int[]{3, 4, -1, 1}));
        assertEquals(3, skyStringUtils.firstMissingPositive(new int[]{1, 2, 0}));
    }
}
