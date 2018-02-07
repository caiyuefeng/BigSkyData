package bean;

import com.sky.data.bean.local.JsonConfBean;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:06 2018/2/5
 * @Modified By:
 */
public class JsonConfBeanTest {

    @Before
    public void setup() {

    }

    /**
     * 测试 JsonConfBean 的 valueOf 方法是否可以成功解析Xml配置文件
     */
    @Test
    public void testValueOf() {
        JsonConfBean jsonConfBean = JsonConfBean.valueOf("./conf/JSONConfiguration.xml");
        assertNotNull(jsonConfBean);
        assertEquals("name", jsonConfBean.getFieldBean().getOutputBean().getName());
        assertEquals("string", jsonConfBean.getFieldBean().getOutputBean().getStoreType());
        assertEquals("姓名", jsonConfBean.getFieldBean().getInputListBean().getInputBean().get(0).getName());
        assertEquals("string", jsonConfBean.getFieldBean().getInputListBean().getInputBean().get(0).getStoreType());
    }

    /**
     * 测试 JsonConfBean 的 toString 方法是否可以序列化对象实例
     */
    @Test
    public void testToJson() {
        JsonConfBean jsonConfBean = JsonConfBean.valueOf("./conf/JSONConfiguration.xml");
        assertNotNull(jsonConfBean);
        assertEquals("{\"field\":{\"output\":{\"name\":\"name\",\"storeType\":\"string\"},\"inputList\":{\"input\":[{\"name\":\"姓名\",\"storeType\":\"string\"}]}}}"
                , jsonConfBean.toString());
    }
}
