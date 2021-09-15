package com.hongrry.blog.api.dao.pojo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-29 13:05
 */
@Data
public class SysUser {
    // @TableId(type = IdType.ASSIGN_ID) //默认 分布式id 雪花算法
    // @TableId(type = IdType.AUTO) // 自增
    private Long id;

    private String account;

    private Integer admin;

    private String avatar;

    private Long createDate;

    private Integer deleted;

    private String email;

    private Long lastLogin;

    private String mobilePhoneNumber;

    private String nickname;

    private String password;

    private String salt;

    private String status;
}

