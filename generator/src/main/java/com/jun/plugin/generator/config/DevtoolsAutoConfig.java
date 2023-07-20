package com.jun.plugin.generator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.generator")
@MapperScan(basePackages = "com.jun.plugin.generator.generate.mapper")
public class DevtoolsAutoConfig {
}
