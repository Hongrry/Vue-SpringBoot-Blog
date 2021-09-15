package com.hongrry.blog.api.dao.vo.params;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-30 19:48
 */
@Data
public class RegisterParams {
    private String account;
    private String password;
    private String nickname;
}
