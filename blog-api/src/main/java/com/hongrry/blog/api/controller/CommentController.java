package com.hongrry.blog.api.controller;

import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.CommentParam;
import com.hongrry.blog.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Hongrry
 * @create 2021-08-31 18:26
 */
@RestController
@RequestMapping("comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("article/{id}")
    public Result getCommentByArticleId(@PathVariable("id") Long id) {
        return commentService.getCommentByArticleId(id);
    }

    @PostMapping("create/change")
    private Result comment(@RequestBody CommentParam commentParam) {
       return commentService.comment(commentParam);
    }
}
