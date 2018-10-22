package com.sky.data.string;

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


    private static Map<String, String> STATUS_MAP = new HashMap<>();

    static {
        STATUS_MAP.put("$^^", "A");
        STATUS_MAP.put("$.^", "B");
        STATUS_MAP.put("$.*", "C");
        STATUS_MAP.put("$*^", "D");
        STATUS_MAP.put(".^^", "E");
        STATUS_MAP.put(".*^", "F");
        STATUS_MAP.put("*^^", "E");
        STATUS_MAP.put("$$", "E");
    }

    public boolean matche(String inputStr, String pattern) {

        char[] status = new char[3];
        List<String> currentArray = new ArrayList<>();
        int index = 0;
        String currentStatus = "";
        for (int i = 0; i < pattern.length(); i++) {
            String currentChar = String.valueOf(pattern.charAt(i));
            if (currentChar.compareTo("a") >= 0 && currentChar.compareTo("b") <= 0) {
                currentStatus += "$";
            }
            String s = STATUS_MAP.get(currentStatus);

        }
        return false;
    }

    private String createStatus(char[] status) {


        return "M";
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

    public static void main(String[] args) {
        SkyStringUtils skyStringUtils = new SkyStringUtils();
        System.out.println("正确答案:42");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("42"));

        System.out.println("正确答案:-42");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("   -42"));

        System.out.println("正确答案:4193");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("4193 with words"));

        System.out.println("正确答案:0");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("words and 987"));

        System.out.println("正确答案:-2147483648");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("-91283472332"));

        System.out.println("正确答案:2147483647");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("20000000000000000000"));

        System.out.println("正确答案:12345678");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("0000000000012345678"));

        System.out.println("正确答案:0");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr("+-2"));


        System.out.println("正确答案:0");
        System.out.println("测试答案:" + skyStringUtils.findNumberFromStr(" +0 123"));
    }
}
