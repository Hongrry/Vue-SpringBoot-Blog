package com.hongrry.blog.controller;

import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hongrry
 * @create 2021-09-03 10:26
 */
@RestController
@RequestMapping("categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public Result categories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("detail")
    public Result categoriesDetail() {
        return categoryService.findAllCategoriesDetail();
    }

    @GetMapping("detail/{id}")
    public Result categoryDetailById(@PathVariable("id") Long id) {
        return categoryService.categoryDetailById(id);
    }
}
