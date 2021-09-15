package com.hongrry.blog.api.controller;

import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Hongrry
 * @create 2021-08-29 15:40
 */
@RestController
@RequestMapping("tags")
public class TagsController {
    @Autowired
    TagService tagService;

    @GetMapping
    public Result tags() {
        return tagService.findAllTags();
    }

    @GetMapping("detail")
    public Result tagsDetail() {
        return tagService.findAllTagsDetail();
    }

    @GetMapping("detail/{id}")
    public Result tagDetailById(@PathVariable("id") Long id) {
        return tagService.findAllTagDetailById(id);
    }


    @GetMapping("hot")
    public Result hot() {
        return tagService.hots(4);
    }
}
