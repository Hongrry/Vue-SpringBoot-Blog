package com.hongrry.blog.admin.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Hongrry
 * @create 2021-09-15 15:30
 */
public interface AuthService {
    boolean auth(HttpServletRequest request, Authentication authentication);
}
