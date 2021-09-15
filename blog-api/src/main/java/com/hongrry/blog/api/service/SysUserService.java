package com.hongrry.blog.api.service;


import com.hongrry.blog.api.dao.pojo.SysUser;
import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.UserVo;
import org.apache.ibatis.annotations.Param;

/**
 * @author Hongrry
 * @create 2021-08-29 15:11
 */
public interface SysUserService {
    /**
     * 根据 id 查询用户
     *
     * @param id 用户id
     * @return 用户
     */
    SysUser findSysUserById(@Param("id") long id);

    /**
     * 根据账号查询用户
     *
     * @param account  账号
     * @param password 密码
     * @return SysUser
     */
    SysUser findUserByAccount(String account, String password);

    /**
     * 根据账号查询用户
     *
     * @param account 账号
     * @return SysUser
     */
    SysUser findUserByAccount(String account);

    /**
     * 根据 token 查询用户信息
     *
     * @param token token
     * @return result
     */
    Result getUserInfoByToken(String token);

    /**
     * 保存用户
     *
     * @param sysUser 注册用户
     */
    void save(SysUser sysUser);

    /**
     * 根据 id 查询用户
     *
     * @param authorId 作者id
     * @return userVo
     */
    UserVo findUserVoById(Long authorId);
}
