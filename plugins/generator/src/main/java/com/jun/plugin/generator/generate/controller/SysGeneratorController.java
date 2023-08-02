package com.jun.plugin.generator.generate.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jun.plugin.common.utils.DataResult;
import com.jun.plugin.generator.generate.entity.SysGenerator;
import com.jun.plugin.generator.generate.service.ISysGeneratorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 代码生成
 *
 * @author wenbin
 * @version V1.0
 * @date 2020年3月18日
 */
@Api(tags = "系统模块-代码生成")
@Slf4j
@RestController
@RequestMapping("/sysGenerator")
public class SysGeneratorController {
    @Resource
    private ISysGeneratorService sysGeneratorService;

    /**
     * 生成代码
     */
    @ApiOperation(value = "生成")
    @GetMapping("/gen")
    @RequiresPermissions("sysGenerator:add")
    public void code(String tables, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(tables.split(","));

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"manager.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    @ApiOperation(value = "查询分页数据")
    @PostMapping("/listByPage")
    @RequiresPermissions("sysGenerator:list")
    public DataResult findListByPage(@RequestBody SysGenerator vo) {
        IPage<SysGenerator> iPage = sysGeneratorService.selectAllTables(vo.getQueryPage(), vo);
        return DataResult.success(iPage);
    }
}
