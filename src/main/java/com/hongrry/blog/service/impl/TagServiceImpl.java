package com.hongrry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hongrry.blog.dao.mapper.TagMapper;
import com.hongrry.blog.dao.pojo.Tag;
import com.hongrry.blog.service.TagService;
import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.dao.vo.TagVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Hongrry
 * @create 2021-08-29 14:27
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<TagVo> findTagsByArticleId(Long id) {
        List<Tag> tags = tagMapper.findTagsByArticleId(id);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        List<Long> hotsTagIds = tagMapper.findHotsTagIds(limit);
        QueryWrapper<Tag> tagQueryWrapper = new QueryWrapper<>();
        tagQueryWrapper.in("id", hotsTagIds);
        List<Tag> tags = tagMapper.selectList(tagQueryWrapper);

        return Result.success(tags);
    }

    @Override
    public Result findAllTags() {
        LambdaQueryWrapper<Tag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Tag::getId, Tag::getTagName);
        List<Tag> tags = tagMapper.selectList(queryWrapper);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllTagsDetail() {
        List<Tag> tags = tagMapper.selectList(null);
        return Result.success(copyList(tags));
    }

    @Override
    public Result findAllTagDetailById(Long id) {
        Tag tag = tagMapper.selectById(id);
        return Result.success(copy(tag));
    }

    private List<TagVo> copyList(List<Tag> tags) {
        LinkedList<TagVo> tagVos = new LinkedList<>();
        for (Tag tag : tags) {
            tagVos.add(copy(tag));
        }
        return tagVos;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }
}
