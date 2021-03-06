package com.hongrry.blog.api.common.aop;

import com.alibaba.fastjson.JSON;
import com.hongrry.blog.api.common.aop.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Hongrry
 * @create 2021-09-04 10:12
 */
@Component
@Aspect
@Slf4j
public class LogAspect {
    /**
     * 配置切点
     */
    @Pointcut("@annotation(com.hongrry.blog.api.common.aop.annotation.LogAnnotation)")
    public void logPointCut() {
    }

    /**
     * 环绕
     *
     * @param point 切点
     * @return 方法执行结果
     * @throws Throwable 异常
     */

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //保存日志
        recordLog(point, time);
        return result;
    }

    private void recordLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("=====================log start================================");
        log.info("module:{}", logAnnotation.module());
        log.info("operation:{}", logAnnotation.operation());

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method:{}", className + "." + methodName + "()");

//        //请求的参数
        Object[] args = joinPoint.getArgs();
        String params = JSON.toJSONString(args[0]);
        log.info("params:{}", params);


        log.info("execute time : {} ms", time);
        log.info("=====================log end================================");
    }
}
