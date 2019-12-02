package com.sun.mybatisplus.service.impl;

import com.sun.mybatisplus.entity.Test;
import com.sun.mybatisplus.mapper.TestMapper;
import com.sun.mybatisplus.service.ITestService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
