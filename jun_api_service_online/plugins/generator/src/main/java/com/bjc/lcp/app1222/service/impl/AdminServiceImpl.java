package com.bjc.lcp.app1222.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjc.lcp.app1222.entity.AdminEntity;
import com.bjc.lcp.app1222.mapper.AdminMapper;
import com.bjc.lcp.app1222.service.AdminService;

/**
 * @description 管理员服务层实现
 * @author Wujun
 * @date 2023-09-06
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, AdminEntity> implements AdminService {

	@Autowired
    private AdminMapper adminMapper;

}



