package com.hongrry.blog.api.controller;
import com.hongrry.blog.api.dao.pojo.SysUser;
import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.utils.SysUserThreadLocal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongrry
 * @create 2021-08-30 22:51
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public Result test() {
        SysUser sysUser = SysUserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
