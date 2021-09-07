package com.hongrry.blog.handler;

import com.alibaba.fastjson.JSON;
import com.hongrry.blog.dao.pojo.SysUser;
import com.hongrry.blog.dao.vo.ErrorCode;
import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.service.TokenService;
import com.hongrry.blog.utils.SysUserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.Socket;

/**
 * @author Hongrry
 * @create 2021-08-30 22:13
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    /**
     * 1. 需要判断 请求的接口路径 是否为 HandlerMethod ( controller 方法)
     * 2. 判断 token 是否为空，如果为空 未登录
     * 3. 如果 token 不为空，验证 token
     * 4. 如果验证通过，放行
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return 是否放行
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 判断是否为 Controller
        if (!(handler instanceof HandlerMethod)) {
            // handler 可能是 RequestResourcesHandler classpath 下的 static 目录
            return true;
        }

        String token = request.getHeader("Authorization");

        log.info("========================================");
        String uri = request.getRequestURI();
        log.info("request uri :{}", uri);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("========================================");

        // 判断 token 是否为空
        if (StringUtils.isBlank(token)) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return false;
        }

        // 验证 token 是否合法
        SysUser sysUser = tokenService.checkToken(token);
        if (sysUser == null) {
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), ErrorCode.NO_LOGIN.getMsg());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().println(JSON.toJSONString(result));
            return false;
        }

        // 登陆验证成功，放行
        SysUserThreadLocal.set(sysUser);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 不删除会有内存泄漏的风险
        SysUserThreadLocal.remove();
        System.out.println("释放");
    }
}