package com.hongrry.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.hongrry.blog.dao.pojo.SysUser;
import com.hongrry.blog.dao.vo.ErrorCode;
import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.service.TokenService;
import com.hongrry.blog.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author Hongrry
 * @create 2021-08-30 18:57
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public SysUser checkToken(String token) {
        // 检查 token 是否为空
        if (StringUtils.isBlank(token)) {
            return null;
        }

        // 解析 token
        Map<String, Object> objectMap = JwtUtils.checkToken(token);
        if (objectMap == null) {
            return null;
        }

        // 在 redis 中获取
        String sysUser = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (sysUser == null) {
            return null;
        }
        return JSON.parseObject(sysUser, SysUser.class);
    }
}
