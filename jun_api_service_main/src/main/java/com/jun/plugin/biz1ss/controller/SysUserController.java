package com.jun.plugin.biz1ss.controller;
import com.alibaba.fastjson.JSON;
import com.jun.plugin.biz1ss.vo.SysUserVo;
import com.jun.plugin.biz1ss.dto.SysUserDto;
import com.jun.plugin.biz1ss.mapper.SysUserMapper;
import com.jun.plugin.biz1ss.entity.SysUserEntity;
import com.jun.plugin.biz1ss.service.SysUserService;
//import com.bjc.lcp.common.cnt.enums.CntTableNameEnum;
//import com.bjc.lcp.common.cnt.service.CntService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitthub.wujun728.engine.common.DataResult;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* @description 测试用户表
* @author Wujun
* @date 2023-07-11
*/
@Api(tags = "测试用户表-管理")
@Slf4j
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    
    @Resource
    private SysUserMapper sysUserMapper;
    
    @ApiOperation(value = "测试用户表-新增")
    @PostMapping("/add")
    //@RequiresPermissions("sysUser:add")
    public DataResult add(@Validated(SysUserVo.Create.class) @RequestBody SysUserVo vo) {
    	SysUserDto dto = new SysUserDto();
    	BeanUtils.copyProperties(vo, dto);
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserEntity::getId, dto.getId());
        List<SysUserEntity> list = sysUserService.list(queryWrapper);
        if (list.size() > 0) {
            return DataResult.fail("数据已存在");
        }
        SysUserEntity entity = new SysUserEntity();
        
        BeanUtils.copyProperties(dto, entity);
        return DataResult.success(sysUserService.save(entity));
    }
    
    @ApiOperation(value = "测试用户表-删除")
    @DeleteMapping("/remove")
    //@RequiresPermissions("sysUser:remove")
    public DataResult delete(@Validated(SysUserVo.Delete.class) @RequestBody SysUserVo vo) {
    	SysUserDto dto = new SysUserDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return DataResult.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserEntity::getId, dto.getId());
        return DataResult.success(sysUserService.remove(queryWrapper));
    }

    @ApiOperation(value = "测试用户表-删除")
    @DeleteMapping("/delete")
    //@RequiresPermissions("sysUser:delete")
    public DataResult delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        return DataResult.success(sysUserService.removeByIds(ids));
    }


    @ApiOperation(value = "测试用户表-更新")
    @PutMapping("/update")
    //@RequiresPermissions("sysUser:update")
    public DataResult update(@Validated(SysUserVo.Update.class) @RequestBody SysUserVo vo) {
    	SysUserDto dto = new SysUserDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return DataResult.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserEntity::getId, dto.getId());
        SysUserEntity entity = sysUserService.getOne(queryWrapper);;
        if (entity == null) {
            //return DataResult.fail("数据不存在");
            entity = new SysUserEntity();
        }
        BeanUtils.copyProperties(dto, entity);
        return DataResult.success(sysUserService.saveOrUpdate(entity));
    }
    


    @ApiOperation(value = "测试用户表-查询单条")
    @RequestMapping(value = "/getOne",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("sysUser:getOne")
    public DataResult getOne(@RequestBody SysUserVo vo) {
    	SysUserDto dto = new SysUserDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return DataResult.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(SysUserEntity::getId, dto.getId());
        SysUserEntity entity = sysUserService.getOne(queryWrapper);;
        return DataResult.success(entity);
    }
    
    


    @ApiOperation(value = "测试用户表-查询列表分页数据")
    @RequestMapping(value = "/listByPage",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("sysUser:listByPage")
    public DataResult listByPage(@RequestBody SysUserVo sysUser) {
        Page page = new Page(sysUser.getPage(), sysUser.getLimit());
        SysUserDto dto = new SysUserDto();
    	BeanUtils.copyProperties(sysUser, dto);
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(sysUser.getId())) {
            queryWrapper.eq(SysUserEntity::getId, dto.getId());
        }
        if (!ObjectUtils.isEmpty(sysUser.getAge())) {
            queryWrapper.eq(SysUserEntity::getAge, dto.getAge());
        }
        if (!ObjectUtils.isEmpty(sysUser.getName())) {
            queryWrapper.eq(SysUserEntity::getName, dto.getName());
        }
        IPage<SysUserEntity> iPage = sysUserService.page(page, queryWrapper);
        return DataResult.success(iPage);
    }
    
    @ApiOperation(value = "测试用户表-查询全部列表数据")
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("sysUser:list")
    public DataResult findListByPage(@RequestBody SysUserVo sysUser) {
        LambdaQueryWrapper<SysUserEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(sysUser.getId())) {
            queryWrapper.eq(SysUserEntity::getId, sysUser.getId());
        }
        if (!ObjectUtils.isEmpty(sysUser.getAge())) {
            queryWrapper.eq(SysUserEntity::getAge, sysUser.getAge());
        }
        if (!ObjectUtils.isEmpty(sysUser.getName())) {
            queryWrapper.eq(SysUserEntity::getName, sysUser.getName());
        }
        List<SysUserEntity> list = sysUserService.list(queryWrapper);
        return DataResult.success(list);
    }


}

