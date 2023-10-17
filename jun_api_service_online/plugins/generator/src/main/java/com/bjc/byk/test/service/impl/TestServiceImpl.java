package com.bjc.byk.test.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjc.byk.test.entity.TestEntity;
import com.bjc.byk.test.mapper.TestMapper;
import com.bjc.byk.test.service.TestService;

/**
 * @description 服务层实现
 * @author Wujun
 * @date 2023-10-17
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestEntity> implements TestService {

	@Autowired
    private TestMapper testMapper;

}



