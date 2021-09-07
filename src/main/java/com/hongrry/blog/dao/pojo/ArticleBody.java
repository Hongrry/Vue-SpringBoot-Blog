package com.hongrry.blog.dao.pojo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-31 12:07
 */
@Data
public class ArticleBody {
    private Long id;
    private String content;
    private String contentHtml;
    private Long articleId;
}
