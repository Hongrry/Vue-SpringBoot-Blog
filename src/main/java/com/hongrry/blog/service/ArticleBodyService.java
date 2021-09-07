package com.hongrry.blog.service;

import com.hongrry.blog.dao.vo.ArticleBodyVo;

/**
 * @author Hongrry
 * @create 2021-08-31 13:08
 */
public interface ArticleBodyService {
    /**
     * 通过文章 id 查询文章内容
     *
     * @param id 文章id
     * @return result
     */
    ArticleBodyVo findArticleBodyById(Long id);
}
