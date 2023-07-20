package com.jun.plugin.job.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.job")
@MapperScan(basePackages = "com.jun.plugin.job.mapper")
@ServletComponentScan(basePackages = {"com.jun.plugin.job"})
public class JobAutoConfig {
}
