package com.hongrry.blog.controller;

import com.hongrry.blog.common.aop.annotation.LogAnnotation;
import com.hongrry.blog.common.cache.Cache;
import com.hongrry.blog.dao.vo.params.ArticleParam;
import com.hongrry.blog.service.ArticleService;
import com.hongrry.blog.dao.vo.params.PageParams;
import com.hongrry.blog.dao.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hongrry
 * @create 2021-08-29 13:07
 */
@RestController
@RequestMapping("articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping
    @LogAnnotation(module = "文章", operation = "获取文章列表")
    @Cache(name = "listArticle")
    public Result listArticle(@RequestBody PageParams pageParams) {
        return articleService.listArticle(pageParams);
    }

    @Cache(name = "hotArticle")
    @PostMapping("hot")
    public Result hotArticle() {
        return articleService.hostArticle(5);
    }

    @Cache(name = "newArticle")
    @PostMapping("new")
    public Result newArticle() {
        return articleService.newArticle(5);
    }

    @PostMapping("listArchives")
    public Result listArchives() {
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id) {
        return articleService.findArticleById(id);
    }

    @PostMapping("publish")
    public Result publish(@RequestBody ArticleParam articleParam) {
        return articleService.publish(articleParam);
    }
}
