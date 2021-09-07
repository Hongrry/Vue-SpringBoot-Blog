package com.hongrry.blog.utils;

import com.hongrry.blog.dao.pojo.SysUser;

/**
 * @author Hongrry
 * @create 2021-08-31 10:19
 */
public class SysUserThreadLocal {
    private SysUserThreadLocal() {
    }

    /**
     * 线程变量隔离 线程本地变量
     */
    public static final ThreadLocal<SysUser> SYS_USER_THREAD_LOCAL = new ThreadLocal<>();

    public static void set(SysUser sysUser) {
        SYS_USER_THREAD_LOCAL.set(sysUser);
    }

    public static SysUser get() {
        return SYS_USER_THREAD_LOCAL.get();
    }

    public static void remove() {
        SYS_USER_THREAD_LOCAL.remove();
    }
}
