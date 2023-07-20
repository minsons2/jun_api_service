package com.jun.plugin.shiro.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wujun
 * @date 2021/3/19
 */
@ComponentScan(basePackages = "com.jun.plugin.shiro")
//@MapperScan(basePackages = "com.jun.plugin.shiro")
@ServletComponentScan(basePackages = {"com.jun.plugin.shiro"})
public class ShiroAutoConfig {
}
