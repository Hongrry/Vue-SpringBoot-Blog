package com.hongrry.blog.admin.service.impl;

import com.hongrry.blog.admin.dao.pojo.Admin;
import com.hongrry.blog.admin.dao.pojo.Permission;
import com.hongrry.blog.admin.service.AdminService;
import com.hongrry.blog.admin.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Hongrry
 * @create 2021-09-15 15:31
 */
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AdminService adminService;

    @Override
    public boolean auth(HttpServletRequest request, Authentication authentication) {
        String requestURI = request.getRequestURI();
        log.info("request url:{}", requestURI);
        //true代表放行 false 代表拦截
        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)) {
            //未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.findAdminByUserName(username);
        if (admin == null) {
            return false;
        }
        if (admin.getId() == 1) {
            //认为是超级管理员
            return true;
        }
        List<Permission> permissions = adminService.findPermissionsByAdminId(admin.getId());
        requestURI = StringUtils.split(requestURI, '?')[0];
        for (Permission permission : permissions) {
            if (requestURI.equals(permission.getPath())) {
                log.info("权限通过");
                return true;
            }
        }
        return false;
    }
}
