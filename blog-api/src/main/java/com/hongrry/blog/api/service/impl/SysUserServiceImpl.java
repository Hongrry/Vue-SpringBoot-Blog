package com.hongrry.blog.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.hongrry.blog.api.dao.mapper.SysUserMapper;
import com.hongrry.blog.api.dao.pojo.SysUser;
import com.hongrry.blog.api.dao.vo.ErrorCode;
import com.hongrry.blog.api.dao.vo.LoginUserVo;
import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.UserVo;
import com.hongrry.blog.api.service.SysUserService;
import com.hongrry.blog.api.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hongrry
 * @create 2021-08-29 15:13
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TokenService tokenService;

    @Override
    public SysUser findSysUserById(long id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public SysUser findUserByAccount(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount, account);
        if (password != null) {
            queryWrapper.eq(SysUser::getPassword, password);
        }
        queryWrapper.select(SysUser::getAccount, SysUser::getId, SysUser::getNickname, SysUser::getAvatar);
        queryWrapper.last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public SysUser findUserByAccount(String account) {
        return findUserByAccount(account, null);
    }

    /**
     * 1. token 合法性校验
     * 是否为空、是否解析成功、redis 是否存在
     * 2. 如果校验失败 返回错误信息
     * 3. 如果成功，返回 LoginUserVo
     *
     * @param token token
     * @return result
     */
    @Override
    public Result getUserInfoByToken(String token) {
        SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            return Result.fail(ErrorCode.TOKEN_INVALID.getCode(), ErrorCode.TOKEN_INVALID.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setNickname(sysUser.getNickname());
        return Result.success(loginUserVo);
    }

    @Override
    public void save(SysUser sysUser) {
        sysUserMapper.insert(sysUser);
    }

    @Override
    public UserVo findUserVoById(Long authorId) {
        UserVo userVo = new UserVo();
        SysUser sysUser = findSysUserById(authorId);
        BeanUtils.copyProperties(sysUser, userVo);
        return userVo;
    }

}
