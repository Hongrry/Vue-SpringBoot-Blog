package com.hongrry.blog.api.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongrry.blog.api.dao.pojo.Category;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hongrry
 * @create 2021-08-31 12:55
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
