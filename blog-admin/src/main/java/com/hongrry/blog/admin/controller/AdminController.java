package com.hongrry.blog.admin.controller;

import com.hongrry.blog.admin.dao.pojo.Permission;
import com.hongrry.blog.admin.dao.vo.Result;
import com.hongrry.blog.admin.dao.vo.params.PageParam;
import com.hongrry.blog.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hongrry
 * @create 2021-09-15 14:14
 */
@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private PermissionService permissionService;

    @PostMapping("permission/permissionList")
    public Result permissionList(@RequestBody PageParam pageParam) {
        return permissionService.listPermission(pageParam);
    }

    @PostMapping("permission/add")
    public Result add(@RequestBody Permission permission) {
        return permissionService.addPermission(permission);
    }

    @PostMapping("permission/update")
    public Result update(@RequestBody Permission permission) {
        return permissionService.updatePermission(permission);
    }

    @GetMapping("permission/delete/{id}")
    public Result delete(@PathVariable("id") Long id) {
        return permissionService.deletePermission(id);
    }

    @PostMapping("user/userInfo")
    public String userinfo() {
        return "Admin";
    }
}
