package com.hongrry.blog.api.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongrry.blog.api.dao.pojo.Comment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hongrry
 * @create 2021-08-31 18:38
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
