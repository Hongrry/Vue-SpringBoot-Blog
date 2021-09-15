package com.hongrry.blog.api.dao.dos;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-29 19:11
 */
@Data
public class Archives {
    private Integer year;
    private Integer month;
    private long count;
}
