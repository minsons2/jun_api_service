package com.bjc.byk.test.controller;
import com.bjc.byk.test.vo.TestVo;
import com.bjc.byk.test.dto.TestDto;
import com.bjc.byk.test.mapper.TestMapper;
import com.bjc.byk.test.entity.TestEntity;
import com.bjc.byk.test.service.TestService;
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
import org.springframework.web.servlet.ModelAndView;
import com.jun.plugin.common.Result;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
* @Version666
* @description 
* @author Wujun
* @date 2023-10-17
*/
@Api(tags = "-管理")
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;
    
    @Resource
    private TestMapper testMapper;
    
    @ApiOperation(value = "-新增")
    @PostMapping("/add")
    //@RequiresPermissions("test:add")
    public Result add(@Validated(TestVo.Create.class) @RequestBody TestVo vo) {
    	TestDto dto = new TestDto();
    	BeanUtils.copyProperties(vo, dto);
        if (ObjectUtils.isEmpty(dto.getRemark())) {
            return Result.fail("参数[remark]不能为空");
        }
        if (ObjectUtils.isEmpty(dto.getFieldNameTest())) {
            return Result.fail("参数[fieldNameTest]不能为空");
        }
        if (ObjectUtils.isEmpty(dto.getCreateTime())) {
            return Result.fail("参数[createTime]不能为空");
        }
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TestEntity::getId, dto.getId());
        List<TestEntity> list = testService.list(queryWrapper);
        if (list.size() > 0) {
            return Result.fail("数据已存在");
        }
        TestEntity entity = new TestEntity();
        
        BeanUtils.copyProperties(dto, entity);
        return Result.success(testService.save(entity));
    }
    
    @ApiOperation(value = "-删除")
    @DeleteMapping("/remove")
    //@RequiresPermissions("test:remove")
    public Result delete(@Validated(TestVo.Delete.class) @RequestBody TestVo vo) {
    	TestDto dto = new TestDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return Result.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TestEntity::getId, dto.getId());
        return Result.success(testService.remove(queryWrapper));
    }

    @ApiOperation(value = "-删除")
    @DeleteMapping("/delete")
    //@RequiresPermissions("test:delete")
    public Result delete(@RequestBody @ApiParam(value = "id集合") List<String> ids) {
        return Result.success(testService.removeByIds(ids));
    }


    @ApiOperation(value = "-更新")
    @PutMapping("/update")
    //@RequiresPermissions("test:update")
    public Result update(@Validated(TestVo.Update.class) @RequestBody TestVo vo) {
    	TestDto dto = new TestDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return Result.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TestEntity::getId, dto.getId());
        TestEntity entity = testService.getOne(queryWrapper);;
        if (entity == null) {
            //return Result.fail("数据不存在");
            entity = new TestEntity();
        }
        BeanUtils.copyProperties(dto, entity);
        return Result.success(testService.saveOrUpdate(entity));
    }
    


    @ApiOperation(value = "-查询单条")
    @RequestMapping(value = "/getOne",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("test:getOne")
    public Result getOne(@RequestBody TestVo vo) {
    	TestDto dto = new TestDto();
    	BeanUtils.copyProperties(vo, dto);
         if (ObjectUtils.isEmpty(dto.getId())) {
              return Result.fail("参数[id]不能为空");
         }
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TestEntity::getId, dto.getId());
        TestEntity entity = testService.getOne(queryWrapper);;
        return Result.success(entity);
    }
    
    


    @ApiOperation(value = "-查询列表分页数据")
    @RequestMapping(value = "/listByPage",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("test:listByPage")
    public Result listByPage(@RequestBody TestVo test) {
        Page page = new Page(test.getPage(), test.getLimit());
        TestDto dto = new TestDto();
    	BeanUtils.copyProperties(test, dto);
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(test.getId())) {
            queryWrapper.eq(TestEntity::getId, dto.getId());
        }
        if (!ObjectUtils.isEmpty(test.getTitle())) {
            queryWrapper.eq(TestEntity::getTitle, dto.getTitle());
        }
        if (!ObjectUtils.isEmpty(test.getContent())) {
            queryWrapper.eq(TestEntity::getContent, dto.getContent());
        }
        if (!ObjectUtils.isEmpty(test.getRemark())) {
            queryWrapper.eq(TestEntity::getRemark, dto.getRemark());
        }
        if (!ObjectUtils.isEmpty(test.getFieldNameTest())) {
            queryWrapper.eq(TestEntity::getFieldNameTest, dto.getFieldNameTest());
        }
        if (!ObjectUtils.isEmpty(test.getCreateTime())) {
            queryWrapper.eq(TestEntity::getCreateTime, dto.getCreateTime());
        }
        IPage<TestEntity> iPage = testService.page(page, queryWrapper);
        return Result.success(iPage);
    }
    
    @ApiOperation(value = "-查询全部列表数据")
    @RequestMapping(value = "/list",method = {RequestMethod.GET,RequestMethod.POST})
    //@RequiresPermissions("test:list")
    public Result findListByPage(@RequestBody TestVo test) {
        LambdaQueryWrapper<TestEntity> queryWrapper = Wrappers.lambdaQuery();
        if (!ObjectUtils.isEmpty(test.getId())) {
            queryWrapper.eq(TestEntity::getId, test.getId());
        }
        if (!ObjectUtils.isEmpty(test.getTitle())) {
            queryWrapper.eq(TestEntity::getTitle, test.getTitle());
        }
        if (!ObjectUtils.isEmpty(test.getContent())) {
            queryWrapper.eq(TestEntity::getContent, test.getContent());
        }
        if (!ObjectUtils.isEmpty(test.getRemark())) {
            queryWrapper.eq(TestEntity::getRemark, test.getRemark());
        }
        if (!ObjectUtils.isEmpty(test.getFieldNameTest())) {
            queryWrapper.eq(TestEntity::getFieldNameTest, test.getFieldNameTest());
        }
        if (!ObjectUtils.isEmpty(test.getCreateTime())) {
            queryWrapper.eq(TestEntity::getCreateTime, test.getCreateTime());
        }
        List<TestEntity> list = testService.list(queryWrapper);
        return Result.success(list);
    }


}

