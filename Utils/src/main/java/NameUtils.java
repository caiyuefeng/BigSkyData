import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Author: 蔡月峰
 * @Description: 随机生成姓名
 *
 * 掉用方法 create() 即可 随机输出一个姓名
 *
 * @Date : Create in 20:07 2018/2/19
 * @Modified By:
 */
public class NameUtils {

    /**
     * 姓氏数组缓存
     */
    private static List<String> surnameList = new ArrayList<String>();

    private static StringBuilder builder = new StringBuilder();
    private static Random random = new Random();
    static {
        try {
            loadSurname("./Utils/conf/百家姓.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 载入百家姓字典
     * @param file 字典文件
     * @throws IOException
     */
    private static void loadSurname(String file) throws IOException {
        FileReader reader = new FileReader(new File(file));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line ;
        while((line = bufferedReader.readLine())!= null ){
            String[] strings = line.split("\t");
            surnameList.addAll(Arrays.asList(strings));
        }
    }

    /**
     * 随机生成姓氏
     * @return 姓氏
     */
    private static String createSurname(){
        return surnameList.get(random.nextInt(488));
    }

    /**
     * 随机生成名称
     * @return 名称
     * @throws UnsupportedEncodingException
     */
    private static String createName() throws UnsupportedEncodingException {
        int high = 176+Math.abs(random.nextInt(39));
        int low = 139 + Math.abs(random.nextInt(93));
        byte[] bytes = new byte[2];
        bytes[0] = Integer.valueOf(high).byteValue();
        bytes[1] = Integer.valueOf(low).byteValue();
        String value = new String(bytes,"GBK");
        return new String(getUTF8BytesFromGBKString(value),"UTF-8");
    }

    /**
     * GBK 转为 UTF-8 编码
     * @param gbkStr GBK字符串
     * @return UTF-8字符串
     */
    private static byte[] getUTF8BytesFromGBKString(String gbkStr) {
        int n = gbkStr.length();
        byte[] utfBytes = new byte[3 * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int m = gbkStr.charAt(i);
            if (m < 128 && m >= 0) {
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
     * @return 姓名
     * @throws UnsupportedEncodingException
     */
    public static String create() throws UnsupportedEncodingException {
        builder.setLength(0);
        builder.append(createSurname());
        int randomInt = random.nextInt()%2;
        if(randomInt==0) {
            builder.append(createName());
        }
        builder.append(createName());
        return builder.toString();
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(create());
    }
}
