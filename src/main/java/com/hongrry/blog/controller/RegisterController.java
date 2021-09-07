package com.hongrry.blog.controller;

import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.dao.vo.params.RegisterParams;
import com.hongrry.blog.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongrry
 * @create 2021-08-30 19:43
 */
@RestController
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    @PostMapping
    public Result register(@RequestBody RegisterParams registerParams) {
        return loginAndRegisterService.register(registerParams);
    }
}
