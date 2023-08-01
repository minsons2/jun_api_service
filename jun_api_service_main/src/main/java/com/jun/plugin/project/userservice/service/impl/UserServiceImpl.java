package com.jun.plugin.project.userservice.service.impl;

import com.jun.plugin.project.userservice.model.User;
import com.jun.plugin.project.userservice.mapper.UserMapper;
import com.jun.plugin.project.userservice.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author project
 * @since 2020-01-08
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
