package com.hongrry.blog.api.service;



import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.TagVo;

import java.util.List;


/**
 * @author Hongrry
 * @create 2021-08-29 14:25
 */
public interface TagService {
    /**
     * 根据文章 id 查询 tag
     *
     * @param id 文章id
     * @return 标签
     */
    List<TagVo> findTagsByArticleId(Long id);

    /**
     * 获取最热标签
     *
     * @param limit 阈值
     * @return result
     */
    Result hots(int limit);

    /**
     * 查询所有的标签
     *
     * @return result
     */
    Result findAllTags();

    /**
     * 查询所有标签的详细
     *
     * @return
     */
    Result findAllTagsDetail();

    /**
     * 根据id查询标签的详细
     *
     * @param id 标签 id
     * @return result
     */
    Result findAllTagDetailById(Long id);
}
