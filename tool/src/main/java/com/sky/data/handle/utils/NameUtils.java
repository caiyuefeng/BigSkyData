package com.sky.data.handle.utils;

import com.sky.data.handle.common.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: 蔡月峰
 * @Description: 随机生成姓名
 * 掉用方法 create() 即可 随机输出一个姓名
 * @Date : Create in 20:07 2018/2/19
 * @Modified By:
 */
@SuppressWarnings({"unused", "JavaDoc"})
public class NameUtils implements Parameter{

    private static final Logger LOG = LoggerFactory.getLogger(NameUtils.class);

    /**
     * 姓氏数组缓存
     */
    private static final List<String> SURNAME_LIST = new ArrayList<String>();

    /**
     * 字符串拼接器
     */
    private static StringBuilder builder = new StringBuilder();

    /**
     * 随机数
     */
    private static Random random = new Random();

    static {
        try {
            loadSurname();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 私有构造函数,防止实例化
     */
    private NameUtils() {

    }

    /**
     * 载入百家姓字典
     *
     * @throws IOException
     */
    private static void loadSurname() throws IOException {
        InputStream inputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        String line;
        // 加载姓氏字典
        try {
            inputStream = NameUtils.class.getClassLoader().getResourceAsStream("surname.txt");
            reader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(reader);
            // 读取数据解析资源
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(BCP, -1);
                SURNAME_LIST.addAll(Arrays.asList(strings));
            }
        } catch (Exception e) {
            LOG.error("Cannot load surname.txt !");
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }

            if (reader != null) {
                reader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 随机生成姓氏
     *
     * @return 姓氏
     */
    private static String createSurname() {
        return SURNAME_LIST.get(random.nextInt(488));
    }

    /**
     * 随机生成名称
     *
     * @return 名称
     * @throws UnsupportedEncodingException
     */
    private static String createName() throws UnsupportedEncodingException {
        int high = 176 + Math.abs(random.nextInt(39));
        int low = 139 + Math.abs(random.nextInt(93));
        byte[] bytes = new byte[2];
        bytes[0] = Integer.valueOf(high).byteValue();
        bytes[1] = Integer.valueOf(low).byteValue();
        String value = new String(bytes, "GBK");
        return new String(getUTF8BytesFromGBKString(value), "UTF-8");
    }

    /**
     * GBK 转为 UTF-8 编码
     *
     * @param gbkStr GBK字符串
     * @return UTF-8字符串
     */
    private static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128) {
                utfBytes[k++] = (byte) m;
                continue;
            }
            utfBytes[k++] = (byte) (0xe0 | (m >> 12));
            utfBytes[k++] = (byte) (0x80 | ((m >> 6) & 0x3f));
            utfBytes[k++] = (byte) (0x80 | (m & 0x3f));
        }
        if (k < utfBytes.length) {
            byte[] tmp = new byte[k];
            System.arraycopy(utfBytes, 0, tmp, 0, k);
            return tmp;
        }
        return utfBytes;
    }

    /**
     * 随机生成姓名
     *
     * @return 姓名
     * @throws  UnsupportedEncodingException
     */
    public static String create() throws UnsupportedEncodingException {
        builder.setLength(0);
        builder.append(createSurname());
        int randomInt = random.nextInt() % 2;
        if (randomInt == 0) {
            builder.append(createName());
        }
        builder.append(createName());
        return builder.toString();
    }
}
