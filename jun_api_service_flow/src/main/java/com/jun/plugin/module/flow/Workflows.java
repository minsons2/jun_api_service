package com.jun.plugin.module.flow;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "workflows")
public class Workflows {
	
    public static List<Map<String, String>> list;   //static 才能拿配置值

    public static List<Map<String, String>> getList() {
        return list;
    }

    public void setList(List<Map<String, String>> list) {
    	Workflows.list = list;
    }

}
