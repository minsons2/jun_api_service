package com.jun.plugin.project.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.project.core.Result;
import com.jun.plugin.project.model.User;
import com.jun.plugin.project.service.IUserService;
import com.jun.plugin.project.utils.JwtUtils;
import com.jun.plugin.project.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

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
