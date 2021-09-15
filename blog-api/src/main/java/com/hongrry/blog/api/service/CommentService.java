package com.hongrry.blog.api.service;


import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.CommentParam;

/**
 * @author Hongrry
 * @create 2021-08-31 18:36
 */
public interface CommentService {
    /**
     * 根据文章 id 查询评论列表
     *
     * @param id 文章id
     * @return result
     */
    Result getCommentByArticleId(Long id);

    /**
     * 评论
     *
     * @param commentParam 评论参数
     * @return result
     */
    Result comment(CommentParam commentParam);

}
