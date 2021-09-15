package com.hongrry.blog.admin.service.impl;

import com.hongrry.blog.admin.dao.pojo.Admin;
import com.hongrry.blog.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Hongrry
 * @create 2021-09-15 15:22
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.findAdminByUserName(username);
        if (admin == null) {
            return null;
        }
        ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new User(admin.getUsername(), admin.getPassword(), grantedAuthorities);
    }
}
