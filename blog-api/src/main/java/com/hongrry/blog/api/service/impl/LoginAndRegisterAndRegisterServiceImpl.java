package com.hongrry.blog.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.hongrry.blog.api.dao.pojo.SysUser;
import com.hongrry.blog.api.dao.vo.ErrorCode;
import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.LoginParams;
import com.hongrry.blog.api.dao.vo.params.RegisterParams;
import com.hongrry.blog.api.service.LoginAndRegisterService;
import com.hongrry.blog.api.service.SysUserService;
import com.hongrry.blog.api.utils.JwtUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author Hongrry
 * @create 2021-08-29 22:48
 */
@Service
public class LoginAndRegisterAndRegisterServiceImpl implements LoginAndRegisterService {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private static final String salt = "mszlu!@#";

    /**
     * 1. 检查参数是否合法
     * 2. 根据用户名和密码去 user 表中查询 是否存在
     * 3. 如果不存在 登陆失败
     * 4. 如果存在，使用 jwt 生成token，返回给前端
     * 5. token 放入 redis 中，token:user信息 设置过期时间
     * (登陆认证的时候，先认证 token 字符串是否合法，去 redis 认证是否存在)
     *
     * @param loginParams 登陆参数
     * @return result
     */
    @Override
    public Result login(LoginParams loginParams) {
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();

        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password = DigestUtils.md5Hex(password);
        SysUser sysUser = sysUserService.findUserByAccount(account, password);

        if (sysUser == null) {
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }

        String token = JwtUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }

    /**
     * 1. 判断参数是否合法
     * 2. 判断账户是否存在，存在 则返回账户已经被注册
     * 3. 不存在，注册用户
     * 4. 生成 token
     * 5. 存入 redis 并且返回
     * 6. 注意 需要加上事务，一旦中间出现问题，需要进行回滚
     *
     * @param registerParams 注册参数
     * @return result
     */
    @Override
    public Result register(RegisterParams registerParams) {
        if (StringUtils.isBlank(registerParams.getAccount())
                || StringUtils.isBlank(registerParams.getPassword())
                || StringUtils.isBlank(registerParams.getNickname())
        ) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(registerParams.getAccount());
        if (sysUser != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }

        sysUser = new SysUser();
        sysUser.setAccount(registerParams.getAccount());
        sysUser.setPassword(DigestUtils.md5Hex(registerParams.getPassword()));
        sysUser.setNickname(registerParams.getNickname());
        sysUserService.save(sysUser);

        String token = JwtUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
        return Result.success(token);
    }
}
