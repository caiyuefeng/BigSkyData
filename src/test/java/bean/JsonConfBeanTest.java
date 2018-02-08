package bean;

import com.sky.data.bean.parse.JsonConfBean;
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

    @Test
    public void testValueOf() {
        JsonConfBean jsonConfBean = JsonConfBean.valueOf("./conf/JSONConfiguration.xml");
        assertNotNull(jsonConfBean);
        assertEquals("name", jsonConfBean.getFieldBean().get(0).getOutputBean().getName());
        assertEquals("string", jsonConfBean.getFieldBean().get(0).getOutputBean().getStoreType());
        assertEquals("姓名", jsonConfBean.getFieldBean().get(0).getInputListBean().getInputBean().get(0).getName());
        assertEquals("string", jsonConfBean.getFieldBean().get(0).getInputListBean().getInputBean().get(0).getStoreType());
    }

    /**
     * 测试 JsonConfBean 的 toString 方法是否可以序列化对象实例
     */
    @Test
    public void testToJson() {
        JsonConfBean jsonConfBean = JsonConfBean.valueOf("./src/test/conf/JSONConfiguration.xml");
        assertNotNull(jsonConfBean);
        assertEquals("{\"field\":[{\"output\":{\"name\":\"name\",\"storeType\":\"string\"},\"inputList\":{\"input\":[{\"name\":\"姓名\",\"storeType\":\"string\"}]}}],\"valueStoreMap\":{},\"keyToOutputKeyMap\":{}}"
                , jsonConfBean.toString());
    }
}
