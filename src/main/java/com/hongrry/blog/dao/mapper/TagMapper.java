package com.hongrry.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongrry.blog.dao.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @author Hongrry
 * @create 2021-08-29 13:06
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章id查询tag
     *
     * @param id 文章id
     * @return 标签列表
     */
    List<Tag> findTagsByArticleId(@Param("id") Long id);

    /**
     * 获取热门标签
     *
     * @param limit 阈值
     * @return 标签列表
     */
    List<Long> findHotsTagIds(@Param("limit") int limit);
}
