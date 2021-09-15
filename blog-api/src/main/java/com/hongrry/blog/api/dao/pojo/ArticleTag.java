package com.hongrry.blog.api.dao.pojo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-09-03 11:00
 */
@Data
public class ArticleTag {
    private Long id;
    private Long articleId;
    private Long tagId;
}
