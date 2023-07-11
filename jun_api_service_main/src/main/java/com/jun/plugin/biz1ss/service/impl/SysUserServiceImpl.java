package com.jun.plugin.biz1ss.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.biz1ss.entity.SysUserEntity;
import com.jun.plugin.biz1ss.mapper.SysUserMapper;
import com.jun.plugin.biz1ss.service.SysUserService;

/**
 * @description 测试用户表服务层实现
 * @author Wujun
 * @date 2023-07-11
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

	@Autowired
    private SysUserMapper sysUserMapper;

}



