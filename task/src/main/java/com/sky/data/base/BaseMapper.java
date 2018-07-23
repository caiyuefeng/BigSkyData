package com.sky.data.base;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: 蔡月峰
 * @Description: hadoop map 基类
 * @Date : Create in 22:33 2018/3/23
 * @Modified By:
 */
public abstract class BaseMapper<KEYIN,VALUEIN,KEYOUT,VALUEOUT> extends Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT> implements BaseCounter {

    /**
     *  输出Key缓存
     */
    private Text NODE_KEY = new Text();

    /**
     * 输出Value缓存
     */
    private Text NODE_VALUE = new Text();

    /**
     * 输入文件名称
     */
    private String fileName = "";

    @Override
    protected void setup(Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context) throws IOException, InterruptedException {

    }

    @Override
    protected void map(KEYIN key, VALUEIN value, Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context){

    }

    @Override
    protected void cleanup(Mapper<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context){

    }
}
