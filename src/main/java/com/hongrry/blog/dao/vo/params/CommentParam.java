package com.hongrry.blog.dao.vo.params;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-09-02 10:07
 */
@Data
public class CommentParam {
    private Long articleId;
    private String content;
    private Long parent;
    private Long toUserId;
}
