package com.jun.plugin.project;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;
import cn.hutool.setting.dialect.PropsUtil;
import com.alibaba.fastjson.JSON;
import com.gitthub.wujun728.engine.generator.GenUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class Test {


    public static void main(String[] args) {
        List<String> filenames = FileUtil.readLines(new File("D:\\Documents\\Desktop\\mp4.txt"),"UTF-8");
        List<String> urls = FileUtil.readLines(new File("D:\\Documents\\Desktop\\new_add_urls.txt"),"UTF-8");
        List<String> urlsNew = Lists.newArrayList();
        List<String> urlsNew2 = Lists.newArrayList();
        for(String url : urls){
            if(!filenames.stream().anyMatch(i->{
                return FileNameUtil.getName(url).equalsIgnoreCase(i);
            })){
                urlsNew.add(url);
            }else{
                urlsNew2.add(url);
            }
        }
        FileUtil.writeUtf8Lines(urlsNew,"D:\\Documents\\Desktop\\new.txt");
        FileUtil.writeUtf8Lines(urlsNew2,"D:\\Documents\\Desktop\\exists.txt");

//        String result = HttpUtil.post("https://gitlab.billjc.com/oauth/token?grant_type=password", "{\n" +
//                "    \"username\": \"wujun82921\",\n" +
//                "    \"password\": \"Abcde123456\"\n" +
//                "}");
//        Console.log(result);
    }



}
