package com.example.demo.controller;

import com.example.demo.service.HelloService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lingjian
 * @createDate: 2020/8/20 21:48
 */
@RestController
@Api(tags = {"测试控制层"})
public class HelloController {

    @Autowired
    private HelloService service;

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/test")
    @ApiOperation("测试定时任务")
    public String test(Long id) {
        service.test(id);
        return "success";
    }

}
