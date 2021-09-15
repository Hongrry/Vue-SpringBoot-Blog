package com.hongrry.blog.admin.service;
import com.hongrry.blog.admin.dao.pojo.Admin;
import com.hongrry.blog.admin.dao.pojo.Permission;

import java.util.List;

/**
 * @author Hongrry
 * @create 2021-09-15 15:24
 */
public interface AdminService {
    /**
     * 查找用户
     *
     * @param username
     * @return
     */
    Admin findAdminByUserName(String username);

    /**
     * 根据管理员 id 查询权限
     *
     * @param adminId
     * @return
     */
    List<Permission> findPermissionsByAdminId(Long adminId);
}
