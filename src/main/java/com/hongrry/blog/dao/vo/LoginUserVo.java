package com.hongrry.blog.dao.vo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-30 17:51
 */
@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
