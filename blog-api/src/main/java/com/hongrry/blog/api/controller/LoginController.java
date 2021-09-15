package com.hongrry.blog.api.controller;

import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.LoginParams;
import com.hongrry.blog.api.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
