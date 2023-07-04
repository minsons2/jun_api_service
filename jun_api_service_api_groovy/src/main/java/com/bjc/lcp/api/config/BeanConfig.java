//package com.bjc.lcp.api.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.dromara.streamquery.stream.core.clazz.ClassHelper;
//import org.dromara.streamquery.stream.plugin.mybatisplus.engine.mapper.DynamicMapperHandler;
//import org.springframework.context.annotation.Bean;
//
//import java.util.List;
//
//public class BeanConfig {
//
//    @Bean
//    public DynamicMapperHandler dynamicMapperHandler(SqlSessionFactory sqlSessionFactory) throws Exception {
//        // 使用ClassHelper的scanClasses方法扫描对应路径下的po生成Class文件集合放入第二个参数就可以了
//        final List<Class<?>> entityClassList= ClassHelper.scanClasses("com.bjc.lcp.api.code");
//        return new DynamicMapperHandler(sqlSessionFactory,entityClassList);
//    }
//}
