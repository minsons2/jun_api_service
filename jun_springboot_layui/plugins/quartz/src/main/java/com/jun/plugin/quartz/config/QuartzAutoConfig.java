package com.jun.plugin.quartz.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.quartz")
@MapperScan(basePackages = "com.jun.plugin.quartz.mapper")
public class QuartzAutoConfig {
}
