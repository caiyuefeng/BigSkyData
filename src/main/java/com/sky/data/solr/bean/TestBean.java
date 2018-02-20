package com.sky.data.solr.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:35 2018/2/14
 * @Modified By:
 */
public class TestBean {

    @Field
    private String id;

    @Field
    private String name;

    @Field
    private String age;

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
