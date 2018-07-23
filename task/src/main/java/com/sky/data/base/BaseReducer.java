package com.sky.data.base;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @Author: 蔡月峰
 * @Description: Hadoop Reduce 基类接口
 * @Date : Create in 22:33 2018/3/23
 * @Modified By:
 */
public abstract class BaseReducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>  extends Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT> implements BaseCounter {

    /**
     * 输出Key
     */
    private Text NODE_KEY = new Text();

    /**
     * 输出Value
     */
    private Text NODE_VALUE = new Text();

    @Override
    public void setup(Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context){

    }

    @Override
    public void reduce(KEYIN text ,Iterable<VALUEIN> values,Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context){

    }

    @Override
    public void cleanup(Reducer<KEYIN, VALUEIN, KEYOUT, VALUEOUT>.Context context){

    }
}
