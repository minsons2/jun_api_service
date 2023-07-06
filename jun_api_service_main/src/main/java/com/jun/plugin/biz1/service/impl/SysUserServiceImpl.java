package com.jun.plugin.biz1.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jun.plugin.biz1.entity.SysUserEntity;
import com.jun.plugin.biz1.mapper.SysUserMapper;
import com.jun.plugin.biz1.service.SysUserService;

/**
 * @description 服务层实现
 * @author wujun
 * @date 2023-07-06
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

	@Autowired
    private SysUserMapper sysUserMapper;

}



