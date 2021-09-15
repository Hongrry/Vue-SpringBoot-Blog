package com.hongrry.blog.api.dao.pojo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-31 12:09
 */
@Data
public class Category {
    private Long id;
    private String avatar;
    private String categoryName;
    private String description;
}
