package com.xeg.xms.controller;

import com.xeg.xms.common.lang.Result;
import com.xeg.xms.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    IUserService userService;

    // http://localhost:8081/test
    @GetMapping("/test")
    public Result test(){
        return Result.succ(userService.list());
    }
}
