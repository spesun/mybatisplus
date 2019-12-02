package com.sun.mybatisplus.web;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sun.mybatisplus.entity.Test;
import com.sun.mybatisplus.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.mapper.Wrapper;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mht
 * @since 2019-11-29
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    ITestService testService;

    @PostMapping("/save")
    public void save(@RequestBody Test test) {
        testService.insert(test);
    }

    @PutMapping("/page")
    public Page selectPage() {
        Test test = new Test();
        test.setId(1);
        EntityWrapper queryWrapper = new EntityWrapper();
        queryWrapper.setEntity(test);

        Page<Test> page = new Page<>();
        page.setCurrent(0);
        page.setSize(100);
        page = testService.selectPage(page, queryWrapper);
        return page;
    }


}
