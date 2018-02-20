package com.sky.data.solr.thread;

import com.sky.data.solr.bean.TestBean;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @Author: 蔡月峰
 * @Description:
 * @Date : Create in 22:08 2018/2/14
 * @Modified By:
 */
public class CoreFactory {


    public static EmbeddedSolrServer getCore(){

        CoreContainer coreContainer = CoreContainer.createAndLoad(Paths.get("D:\\solrHome"));

        EmbeddedSolrServer solrServer = new EmbeddedSolrServer(coreContainer,"index0");

        return solrServer;

    }


    public static void main(String[] args) throws IOException, SolrServerException {
        TestBean testBean = new TestBean();
        testBean.setAge("24");
        testBean.setName("蔡月峰");
        testBean.setId("1");
        EmbeddedSolrServer solrServer = getCore();
        solrServer.addBean(testBean);
        solrServer.commit();
        solrServer.close();
    }




}
