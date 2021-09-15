package com.hongrry.blog.api.dao.pojo;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-31 18:23
 */
@Data
public class Comment {
    private Long id;
    private String content;
    private Long createDate;
    private Long articleId;
    private Long authorId;
    private Long parentId;
    private Long toUid;
    private Integer level;
}
