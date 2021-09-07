package com.hongrry.blog.controller;

import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.dao.vo.params.LoginParams;
import com.hongrry.blog.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hongrry
 * @create 2021-08-29 22:40
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    LoginAndRegisterService loginAndRegisterService;

    @PostMapping
    public Result login(@RequestBody LoginParams loginParams) {
        return loginAndRegisterService.login(loginParams);
    }
}
