package com.sky.data.string;

import org.apache.commons.collections.list.TreeList;

import java.util.*;

/**
 * @author : 蔡月峰
 * @version : 1.0
 * @Description: 字符串处理相关工具类
 * @date : 2018/10/22 10:23
 **/
public class SkyStringUtils {
    private static Set<String> INTEGER_SET = new HashSet<>();

    private final static String PLUS = "+";

    private final static String MINUS = "-";

    private final static int MIN = -2147483648;
    private final static int MAX = 2147483647;

    static {
        INTEGER_SET.add("0");
        INTEGER_SET.add("1");
        INTEGER_SET.add("2");
        INTEGER_SET.add("3");
        INTEGER_SET.add("4");
        INTEGER_SET.add("5");
        INTEGER_SET.add("6");
        INTEGER_SET.add("7");
        INTEGER_SET.add("8");
        INTEGER_SET.add("9");
    }

    private boolean singn = false;


    public boolean matche(String inputStr, String pattern) {
        Map<String, List<String>> STATUS_MAP = new HashMap<>();
        createStatus(pattern, STATUS_MAP);
        String currentStatus = "0";
        for (int i = 0; i < inputStr.length(); i++) {
            String currentChar = String.valueOf(inputStr.charAt(i));
            String fa = STATUS_MAP.get(currentStatus + currentChar).get(0);
            if (fa == null) {
                return false;
            }
            if (STATUS_MAP.get(fa) == null) {
                currentStatus = fa;
                continue;
            }
            String nextStatus = STATUS_MAP.get(fa).get(0);
            if (nextStatus == null) {
                continue;
            }

            if (nextStatus.equals("T")) {
                return true;
            }
            if (nextStatus.compareTo("0") >= 0 && nextStatus.compareTo(String.valueOf(Integer.MAX_VALUE)) <= 0) {
                currentStatus = nextStatus;
            }
        }
        List<String> status = STATUS_MAP.get(currentStatus);
        return status.size() == 2 ? "T".equals(STATUS_MAP.get(currentStatus).get(1)) : "T".equals(STATUS_MAP.get(currentStatus).get(0));
    }

    private void createStatus(String pattern, Map<String, List<String>> STATUS_MAP) {
        int index = 0;
        for (int i = 0; i < pattern.length(); i++) {
            String currentChar = String.valueOf(pattern.charAt(i));
            if (currentChar.compareTo("a") >= 0 && currentChar.compareTo("z") <= 0) {
                List<String> status = new ArrayList<>();
                status.add(String.valueOf(index + 1));
                STATUS_MAP.put(String.valueOf(index) + currentChar, status);
                if (i >= 2) {
                    String lastChar = String.valueOf(pattern.charAt(i - 1));
                    if ("*".equals(lastChar)) {
                        List<String> newStatus = new ArrayList<>();
                        newStatus.add(String.valueOf(index + 1));
                        STATUS_MAP.put(String.valueOf(index - 1) + currentChar, newStatus);
                    }
                }
                index++;
                continue;
            }
            if ("*".equals(currentChar)) {
                List<String> status = new ArrayList<>();
                status.add(String.valueOf(index - 1));
                STATUS_MAP.put(String.valueOf(index), status);
            }
        }
        if (STATUS_MAP.containsKey(String.valueOf(index))) {
            STATUS_MAP.get(String.valueOf(index)).add("T");
            return;
        }
        List<String> status = new ArrayList<>();
        status.add("T");
        STATUS_MAP.put(String.valueOf(index), status);
    }

    public int findNumberFromStr(String str) {
        if (str == null) {
            return 0;
        }
        singn = false;
        int length = 0;
        final StringBuilder builder = new StringBuilder();
        for (int charIndex = 0; charIndex < str.length(); charIndex++) {
            String currentChar = String.valueOf(str.charAt(charIndex));
            if (length == 0 && currentChar.equals(" ")) {
                continue;
            }
            if (length == 0) {
                if (PLUS.equals(currentChar)) {
                    length++;
                    continue;
                }
                if (MINUS.equals(currentChar)) {
                    singn = true;
                    length++;
                    continue;
                }
            }

            if (builder.length() == 0 && "0".equals(currentChar)) {
                length++;
                continue;
            }

            if (!INTEGER_SET.contains(currentChar)) {
                break;
            }
            length++;
            builder.append(currentChar);
            if (builder.length() == 11) {
                return output(builder);
            }
        }
        return output(builder);
    }

    private int output(StringBuilder builder) {
        if (builder.length() == 0) {
            return 0;
        }
        String outStr = builder.toString();
        if (PLUS.equals(outStr)) {
            return 0;
        }
        if (MINUS.equals(outStr)) {
            return 0;
        }
        if (singn) {
            outStr = "-" + outStr;
        }
        long output = Long.parseLong(outStr);
        if (output >= MAX) {
            return MAX;
        }
        if (output <= MIN) {
            return MIN;
        }
        return (int) output;
    }

    /**
     * 判断一个数字是否是回文数字
     * 例如:
     * 121 是回文数字
     * 123 不是回文数字
     *
     * @param num 带检查数字
     * @return 检测结果
     */
    public boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        int dest = num;
        int result = 0;
        while (num != 0) {
            result = result * 10 + num % 10;
            num /= 10;
        }
        return result == dest;
    }

    /**
     * 给定n个非负整数A1，A2，…，An，其中每个表示坐标（i，Ai）上的一个点。
     * 绘制n个垂直线，使得线I的两个端点位于（i，Ai）和（i，0）。
     * 找到两条线，连同X轴形成一个容器，使的面积最大。
     *
     * @param heights Y坐标数组
     * @return 最大面积
     */
    public int maxArea(int[] heights) {
        int area = 0;
        int i = 0;
        int j = heights.length - 1;
        while (i < j) {
            int currentArea = (j - i) * (min(heights[i], heights[j]));
            area = area < currentArea ? currentArea : area;
            if (heights[i] < heights[j]) {
                i++;
            } else if (heights[i] > heights[j]) {
                j--;
            } else {
                i++;
                j--;
            }
        }
        return area;
    }

    /**
     * 求取两值间的较小值
     *
     * @param first  值一
     * @param second 值二
     * @return 较小值
     */
    public int min(int first, int second) {
        return first < second ? first : second;
    }

    /**
     * 将数字转换为罗马数字
     * 罗马数字由七个不同的符号表示：I、V、X、L、C、D和M。
     * 例如，两个在罗马数字中被写为II，只是21个加在一起。十二写为，XII，这是简单的X+II。数字二十七写为XXVII，它是XX+V+II。
     * 罗马数字通常从左到右写得最大到最小。然而，四的数字不是IIII。取而代之的是，数字四被写为IV。
     * 因为一个是在五之前，我们减去了四。同样的原理适用于编号为九的IX。使用减法的有六个实例：
     * I可以放在V（5）和X（10）之前，使之成为4和9。
     * X可以放置在L（50）和C（100）之前，使之成为40和90。
     * C可以放置在D（500）和M（1000）之间，使之成为400和900。
     *
     * @param num 待转换数字
     * @return 转换后罗马数字
     */
    public String parseIntToRomanNumber(int num) {
        int index = 0;
        String unitRoman = "";
        String decadeRoman = "";
        String hundredsRoman = "";
        String kilobitRoman = "";
        while (num > 0) {
            int unit = num % 10;
            if (index == 0) {
                unitRoman = getRoman(unit, new String[]{"I", "IV", "V", "IX"});
            }
            if (index == 1) {
                decadeRoman = getRoman(unit, new String[]{"X", "XL", "L", "XC"});
            }
            if (index == 2) {
                hundredsRoman = getRoman(unit, new String[]{"C", "CD", "D", "CM"});
            }
            if (index == 3) {
                for (int i = 0; i < unit; i++) {
                    kilobitRoman += "M";
                }
            }
            num /= 10;
            index++;
        }
        return kilobitRoman + hundredsRoman + decadeRoman + unitRoman;

    }

    public String getRoman(int num, String[] romanArray) {
        final StringBuilder builder = new StringBuilder();
        if (num < 4) {
            for (int i = 0; i < num; i++) {
                builder.append(romanArray[0]);
            }
            return builder.toString();
        }
        if (num == 4) {
            return romanArray[1];
        }
        if (num == 9) {
            return romanArray[3];
        }
        builder.append(romanArray[2]);
        for (int i = 5; i < num; i++) {
            builder.append(romanArray[0]);
        }
        return builder.toString();
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length < 1) {
            return "";
        }
        String firstChar = strs[0];
        for (int i = 1; i < strs.length; i++) {
            if (strs[i].equals("")) {
                return "";
            }
            for (int j = 0; j < firstChar.length(); j++) {
                if (j >= strs[i].length()) {
                    firstChar = firstChar.substring(0, j);
                    break;
                }
                if (strs[i].charAt(j) != firstChar.charAt(j)) {
                    firstChar = firstChar.substring(0, j);
                    break;
                }
            }
            if (firstChar.equals("")) {
                return "";
            }
        }
        return firstChar;
    }


    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closeNum = Integer.MAX_VALUE;
        int result = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int current = nums[i];
            int startIndex = i + 1;
            int endIndex = nums.length - 1;
            while (startIndex < endIndex) {
                int startNum = nums[startIndex];
                int endNum = nums[endIndex];
                int sum = current + startNum + endNum - target;
                if (sum < 0) {
                    startIndex++;
                } else {
                    endIndex--;
                }
                if (closeNum > Math.abs(sum)) {
                    closeNum = Math.abs(sum);
                    result = current + startNum + endNum;
                }
                if (closeNum == 0) {
                    return result;
                }
            }
        }
        return result;
    }

    private String[] mapping = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        letterCombinationsChild(digits, 0, result, new StringBuilder());
        return result;
    }

    private void letterCombinationsChild(String digits, int index, List<String> result, StringBuilder sb) {
        if (index == digits.length()) {
            if (sb.length() != 0) {
                result.add(sb.toString());
            }
            return;
        }
        for (char ch : mapping[Integer.parseInt(digits.substring(index, index + 1))].toCharArray()) {
            sb.append(ch);
            letterCombinationsChild(digits, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }


}
