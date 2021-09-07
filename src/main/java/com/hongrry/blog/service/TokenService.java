package com.hongrry.blog.service;

import com.hongrry.blog.dao.pojo.SysUser;

/**
 * @author Hongrry
 * @create 2021-08-30 18:56
 */
public interface TokenService {
    /**
     * 检查 token 返回 token 对应的用户
     *
     * @param token token
     * @return result
     */
    SysUser checkToken(String token);
}
