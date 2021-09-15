package com.hongrry.blog.api.dao.vo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-31 12:37
 */
@Data
public class CategoryVo {
    private Long id;
    private String avatar;
    private String categoryName;
    private String description;
}
