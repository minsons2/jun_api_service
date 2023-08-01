package com.jun.plugin.project.base.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class HikariDataSourceConfig {

    //@Primary
    @Bean(name = "_mainDataSource")
    public DataSource datasource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("project.datasource.url"));
        ds.setUsername(env.getProperty("project.datasource.username"));
        ds.setPassword(env.getProperty("project.datasource.password"));
        ds.setDriverClassName(env.getProperty("project.datasource.driver-class-name"));
        return ds;
    }

}