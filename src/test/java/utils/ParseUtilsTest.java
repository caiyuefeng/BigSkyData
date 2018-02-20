package utils;

import com.sky.data.utils.ParseUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:14 2018/2/7
 * @Modified By:
 */
public class ParseUtilsTest {

    /**
     * 测试 ParseUtils 的 Parse 方法 解析复杂JSON数据
     */
    @Test
    public void testParse() {
        assertEquals("{\"汽车颜色\":\"黑色\",\"手机颜色\":\"白色\",\"设备名称\":[\"奥迪\",\"华为\"]}", ParseUtils.parse("{\"car\":{\"code\":\"奥迪\",\"color\":\"黑色\"},\"phone\":{\"code\":\"华为\",\"color\":\"白色\"}}", "1"));
    }

    /**
     * 测试 ParseUtils 的 createKey 方法输出复杂KEY
     */
    @Test
    public void testCreateKey() {
//        ParseUtils parseUtils = new ParseUtils();y
//        try {
//            Method method = parseUtils.getClass().getDeclaredMethod("createKey", String.class, String.class);
//            method.setAccessible(true);
//            Object object = method.invoke(parseUtils, "car", "name");
//            assertEquals("car.name", object);
//        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//            e.printStackTrace();
//        }


    }
}
