package com.jun.plugin.project;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.gitthub.wujun728.engine.base.DataResult;
import com.gitthub.wujun728.engine.base.interfaces.IExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;

@Component
public class CommonDBCompoent implements IExecutor<DataResult,Map<String,Object>> {

    @Autowired
    JdbcTemplate jt;
    static DruidDataSource ds = new DruidDataSource();

    @PostConstruct
    public void test(){
        ds.setUrl(SpringUtil.getProperty("project.datasource.url"));
        ds.setUsername(SpringUtil.getProperty("project.datasource.username"));
        ds.setPassword(SpringUtil.getProperty("project.datasource.password"));
    }

    @Override
    public DataResult execute(Map params) {
        //JdbcTemplate jt = new JdbcTemplate(ds);
        if(jt!=null) {
            System.out.println("jdbcTemplate初始化成功" );
        }
        Object[] sqliParams = Arrays.asList("aaa"+ RandomUtil.randomInt(),"bbb"+ RandomUtil.randomInt(),"cccc"+ RandomUtil.randomInt(),"dddd"+ RandomUtil.randomInt()).toArray() ;
        int icount = jt.update("insert into test (title,content,remark,field_name_test ) VALUES ( ?, ?, ?, ? ) ", sqliParams);
        System.out.println(JSON.toJSONString(DataResult.success(icount)));
        return DataResult.success(icount);
    }

    @Override
    public DataResult rollback(Map params) {
        return null;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("11111111111111111111111111111111111111 22222222222222222222222222 ");
        System.out.println("SpringUtil.getProperty(\"project.datasource.url\")="+SpringUtil.getProperty("project.datasource.url"));
        JdbcTemplate jt1 = SpringUtil.getBean(JdbcTemplate.class);
        if(jt1!=null) {
            System.out.println("jdbcTemplate初始化成功 1111" );
        }else{
            jt1 = SpringUtil.getBean(JdbcTemplate.class);
        }
        Object[] sqliParams = Arrays.asList("aaa"+ RandomUtil.randomInt(),"bbb"+ RandomUtil.randomInt(),"cccc"+ RandomUtil.randomInt(),"dddd"+ RandomUtil.randomInt()).toArray() ;
        int icount = SpringUtil.getBean(JdbcTemplate.class).update("insert into test (title,content,remark,field_name_test ) VALUES ( ?, ?, ?, ? ) ", sqliParams);
        System.out.println("333333333333333333333333333 22222222222222222222222222 ");
    }


}
