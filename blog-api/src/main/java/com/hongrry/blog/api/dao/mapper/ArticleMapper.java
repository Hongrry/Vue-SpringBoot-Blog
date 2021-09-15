package com.hongrry.blog.api.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongrry.blog.api.dao.dos.Archives;
import com.hongrry.blog.api.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hongrry
 * @create 2021-08-29 13:05
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 文章归档
     *
     * @return 归档
     */
    List<Archives> listArchives();


    /**
     * 查询文章列表
     *
     * @param page       分页参数
     * @param categoryId 分类id
     * @param tagId      标签id
     * @param year       年
     * @param month      月
     * @return
     */
    IPage<Article> listArticle(Page<Article> page,
                               @Param("categoryId") Long categoryId,
                               @Param("tagId") Long tagId,
                               @Param("year") String year,
                               @Param("month") String month);

}
