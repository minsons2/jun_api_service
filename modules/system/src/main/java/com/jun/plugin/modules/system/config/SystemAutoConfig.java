package com.jun.plugin.modules.system.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.modules.system")
//@EnableJpaRepositories(basePackages = "com.jun.plugin.modules.system.jpa")
@EntityScan(basePackages = "com.jun.plugin.modules.system.entity")
@MapperScan(basePackages = "com.jun.plugin.modules.system.mapper")
public class SystemAutoConfig {
}
