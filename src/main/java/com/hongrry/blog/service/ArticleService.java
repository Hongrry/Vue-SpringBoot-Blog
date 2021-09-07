package com.hongrry.blog.service;

import com.hongrry.blog.dao.vo.params.ArticleParam;
import com.hongrry.blog.dao.vo.params.PageParams;
import com.hongrry.blog.dao.vo.Result;
import org.apache.ibatis.annotations.Param;

/**
 * @author Hongrry
 * @create 2021-08-29 13:24
 */
public interface ArticleService {
    /**
     * 分页查询 文章列表
     *
     * @param pageParams 分页参数
     * @return result
     */
    Result listArticle(PageParams pageParams);

    /**
     * 获取最热文章
     *
     * @param limit 阈值
     * @return result
     */
    Result hostArticle(@Param("limit") int limit);

    /**
     * 获取最新的文章
     *
     * @param limit 阈值
     * @return result
     */
    Result newArticle(@Param("limit") int limit);

    /**
     * 文章归档
     *
     * @return result
     */
    Result listArchives();

    /**
     * 通过 id 查询文章
     *
     * @param id 文章id
     * @return result
     */
    Result findArticleById(Long id);

    /**
     * 发布文章
     *
     * @param articleParam 文章
     * @return result
     */
    Result publish(ArticleParam articleParam);
}
