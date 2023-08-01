package com.jun.plugin.project.userservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class TestController {

    @RequestMapping("test11")
    public String findById(){
        return "test1";
    }

//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

}
