package com.jun.plugin.project.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jun.plugin.project.core.Result;
import com.jun.plugin.project.utils.JwtUtils;
import com.jun.plugin.project.utils.MD5Utils;
import com.jun.plugin.project.model.User;
import com.jun.plugin.project.service.IUserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author project
 * @since 2020-01-08
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result login(@RequestBody @Valid User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User userO = userService.getOne(queryWrapper);
        if (userO == null) {
            return Result.fail("账号未找到");
        }
        if (!MD5Utils.Encrypt(user.getPassword(),true).equals(userO.getPassword())) {
            return Result.fail("密码错误");
        }
        String token = JwtUtils.geneJsonWebToken(user);
        user.setToken(token);
        user.setPassword("");
        return Result.success(user);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Valid User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User userO = userService.getOne(queryWrapper);
        if (userO != null) {
            return Result.fail("账号已存在");
        }
        user.setPassword(MD5Utils.Encrypt(user.getPassword(),true));
        userService.save(user);
        return Result.success();
    }

    @ApiOperation(value = "删除")
    @PostMapping("delete/{id}")
    public Result delete(@PathVariable("id") Long id){
        userService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value = "更新")
    @PostMapping("update")
    public Result update(@RequestBody User user){
        //密码不更新
        user.setPassword(null);
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation(value = "查询分页数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "currentPage", value = "页码"),
        @ApiImplicitParam(name = "pageCount", value = "每页条数")
    })
    @GetMapping("listByPage")
    public Result findListByPage(@RequestParam(defaultValue = "1") Integer currentPage,
                                   @RequestParam(defaultValue = "10") Integer pageCount){
        Page page = new Page(currentPage, pageCount);
        IPage<User> iPage = userService.page(page);
        return Result.success(iPage);
    }

    @ApiOperation(value = "id查询")
    @GetMapping("getById/{id}")
    public Result findById(@PathVariable Long id){
        return Result.success(userService.getById(id));
    }

}
