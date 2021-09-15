package com.hongrry.blog.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hongrry.blog.api.dao.mapper.CommentMapper;
import com.hongrry.blog.api.dao.pojo.Comment;
import com.hongrry.blog.api.dao.pojo.SysUser;
import com.hongrry.blog.api.dao.vo.CommentVo;
import com.hongrry.blog.api.dao.vo.Result;
import com.hongrry.blog.api.dao.vo.params.CommentParam;
import com.hongrry.blog.api.service.CommentService;
import com.hongrry.blog.api.service.SysUserService;
import com.hongrry.blog.api.utils.SysUserThreadLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Hongrry
 * @create 2021-08-31 18:37
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 1. 根据文章 id 查询评论列表
     * 2. 根据作者的 id 查询作者的信息
     * 3. 如果 level ==1 查询子评论
     *
     * @param id 文章id
     * @return
     */
    @Override
    public Result getCommentByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        queryWrapper.eq(Comment::getLevel, 1);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return Result.success(copyList(comments));
    }

    @Override
    public Result comment(CommentParam commentParam) {
        SysUser sysUser = SysUserThreadLocal.get();

        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId());
        comment.setContent(commentParam.getContent());
        comment.setAuthorId(sysUser.getId());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParam.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        } else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUid = commentParam.getToUserId();
        comment.setToUid(toUid == null ? 0 : toUid);

        commentMapper.insert(comment);

        return Result.success(null);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        LinkedList<CommentVo> commentVos = new LinkedList<>();
        for (Comment comment : comments) {
            commentVos.add(copy(comment));
        }
        return commentVos;
    }

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        // 查询作者
        commentVo.setAuthor(sysUserService.findUserVoById(comment.getAuthorId()));
        // 查询子评论
        if (comment.getLevel() == 1) {
            List<CommentVo> list = findCommentByParentId(comment.getId());
            commentVo.setChildrens(list);
        }
        // toUser
        if (comment.getLevel() > 1) {
            commentVo.setToUser(sysUserService.findUserVoById(comment.getToUid()));
        }
        return commentVo;
    }

    private List<CommentVo> findCommentByParentId(Long parentId) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId, parentId);
        queryWrapper.eq(Comment::getLevel, 2);
        List<Comment> comments = commentMapper.selectList(queryWrapper);
        return copyList(comments);
    }

}
