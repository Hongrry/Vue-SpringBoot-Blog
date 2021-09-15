package com.hongrry.blog.api.controller;


import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.service.LoginAndRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongrry
 * @create 2021-08-30 19:34
 */
@RestController
@RequestMapping("logout")
public class LogoutController {
    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    @GetMapping
    public Result logout(@RequestHeader("Authorization") String token) {
        return loginAndRegisterService.logout(token);
    }

}
