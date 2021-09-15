package com.hongrry.blog.api.service;


import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.LoginParams;
import com.hongrry.blog.api.dao.vo.params.RegisterParams;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Hongrry
 * @create 2021-08-29 22:44
 */
@Transactional(rollbackFor = Exception.class)
public interface LoginAndRegisterService {
    /**
     * 登陆功能
     *
     * @param loginParams 登陆参数
     * @return result
     */
    Result login(LoginParams loginParams);

    /**
     * 注销功能
     *
     * @param token token
     * @return result
     */
    Result logout(String token);

    /**
     * 注册功能
     *
     * @param registerParams 注册参数
     * @return result
     */
    Result register(RegisterParams registerParams);
}
