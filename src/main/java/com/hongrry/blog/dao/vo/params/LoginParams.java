package com.hongrry.blog.dao.vo.params;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-29 22:43
 */

@Data
public class LoginParams {
    private String account;
    private String password;
}
