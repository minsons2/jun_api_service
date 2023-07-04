package com.jun.plugin.system.common.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.log.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 资源搜集器，将项目中所有接口（带@RequestMapping的）都搜集起来
 * <p>
 * 搜集到的接口会被缓存，用于请求时判断请求的接口是否存在
 *
 */
@Component
public class ResourceCollectListener implements CommandLineRunner {

    private static final Log log = Log.get();

//    @Resource
//    private ResourceCache resourceCache;

    private final Set<String> resourceCaches = CollectionUtil.newHashSet();

    @Override
    public void run(String... args) {
        //1.获取所有后端接口
        Set<String> urlSet = CollectionUtil.newHashSet();
        Map<String, RequestMappingHandlerMapping> mappingMap = SpringUtil.getApplicationContext().getBeansOfType(RequestMappingHandlerMapping.class);
        Collection<RequestMappingHandlerMapping> mappings = mappingMap.values();
        for (RequestMappingHandlerMapping mapping : mappings) {
            Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
            map.keySet().forEach(requestMappingInfo -> {
                Set<String> patterns = requestMappingInfo.getPatternsCondition().getPatterns();
                urlSet.addAll(patterns);
            });
        }
        //2.汇总添加到缓存
        this.putAllResources(urlSet);
        log.info(">>> 缓存资源URL集合完成!资源数量：{}", urlSet.size());
    }



    /**
     * 获取所有缓存资源
     *
     */
    public Set<String> getAllResources() {
        return resourceCaches;
    }


    /**
     * 直接缓存所有资源
     *
     */
    public void putAllResources(Set<String> resources) {
        resourceCaches.addAll(resources);
    }
}
