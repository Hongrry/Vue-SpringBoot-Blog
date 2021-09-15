package com.hongrry.blog.api.service;


import com.hongrry.blog.api.dao.vo.CategoryVo;
import com.hongrry.blog.api.dao.vo.Result;

/**
 * @author Hongrry
 * @create 2021-08-31 13:05
 */
public interface CategoryService {
    /**
     * 通过 id 查找分类
     *
     * @param id 分类id
     * @return
     */
    CategoryVo findCategoryById(Long id);

    /**
     * 获取所有分类
     *
     * @return result
     */
    Result findAllCategories();

    /**
     * 获取所有分类详细
     *
     * @return
     */
    Result findAllCategoriesDetail();

    Result categoryDetailById(Long id);

}
