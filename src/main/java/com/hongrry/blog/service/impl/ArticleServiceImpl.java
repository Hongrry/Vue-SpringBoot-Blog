package com.hongrry.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongrry.blog.dao.mapper.ArticleBodyMapper;
import com.hongrry.blog.dao.mapper.ArticleMapper;
import com.hongrry.blog.dao.mapper.ArticleTagMapper;
import com.hongrry.blog.dao.pojo.Article;
import com.hongrry.blog.dao.pojo.ArticleBody;
import com.hongrry.blog.dao.pojo.ArticleTag;
import com.hongrry.blog.dao.pojo.SysUser;
import com.hongrry.blog.dao.vo.TagVo;
import com.hongrry.blog.dao.vo.params.ArticleParam;
import com.hongrry.blog.service.*;
import com.hongrry.blog.dao.vo.ArticleVo;
import com.hongrry.blog.dao.vo.params.PageParams;
import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.utils.SysUserThreadLocal;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Hongrry
 * @create 2021-08-29 13:26
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleBodyService articleBodyService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ThreadService threadService;
    @Autowired
    private ArticleTagMapper articleTagMapper;
    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    /**
     * 分页查询 article 数据库表
     *
     * @param pageParams 分页参数
     * @return 结果
     */
    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(page,pageParams.getCategoryId(),pageParams.getTagId(),pageParams.getYear(),pageParams.getMonth());
        return Result.success(copyList(articleIPage.getRecords(),true,true));
    }

    @Override
    public Result hostArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts)
                .select(Article::getId, Article::getTitle)
                .last("limit " + limit);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result newArticle(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate)
                .select(Article::getTitle, Article::getId);
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles, false, false));
    }

    @Override
    public Result listArchives() {
        return Result.success(articleMapper.listArchives());
    }

    @Override
    public Result findArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        ArticleVo articleVo = copy(article, true, true, true, true);
        // 更新阅读量 多线程进行，不能干扰主线程
        threadService.updateArticleViewCount(articleMapper, article);
        return Result.success(articleVo);
    }

    /**
     * 需要登录拦截
     * 1. 发布文章 构建 article对象
     * 2. 作者 id 当前登录用户
     * 3. 标签加入到关联表中
     * 4.
     *
     * @param articleParam 文章
     * @return
     */
    @Override
    public Result publish(ArticleParam articleParam) {
        // 获取当前用户
        SysUser sysUser = SysUserThreadLocal.get();
        // 构建 article 对象
        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        article.setCommentCounts(0L);
        article.setCreateDate(System.currentTimeMillis());
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0L);
        article.setWeight(0);
        // mybatis plus 会自动设置对象的 id
        articleMapper.insert(article);
        // tag 关联
        List<TagVo> tag = articleParam.getTags();
        for (TagVo tagVo : tag) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setArticleId(article.getId());
            articleTag.setTagId(tagVo.getId());
            articleTagMapper.insert(articleTag);

        }
        // body 关联
        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);

        // 更新
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        Map<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);
    }

    private List<ArticleVo> copyList(List<Article> records,
                                     boolean isTag,
                                     boolean isAuthor) {
        LinkedList<ArticleVo> articleVos = new LinkedList<>();
        for (Article record : records) {
            articleVos.add(copy(record, isTag, isAuthor));
        }
        return articleVos;
    }

    /**
     * 对象转换
     */
    private ArticleVo copy(Article article,
                           boolean isTag,
                           boolean isAuthor) {
        return copy(article, isTag, isAuthor, false, false);
    }

    /**
     * 对象转换
     */
    private ArticleVo copy(Article article,
                           boolean isTag,
                           boolean isAuthor,
                           boolean isBody,
                           boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        // 复制相同的属性
        BeanUtils.copyProperties(article, articleVo);
        // 转换时间
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag) {
            articleVo.setTags(tagService.findTagsByArticleId(article.getId()));
        }
        if (isAuthor) {
            articleVo.setAuthor(sysUserService.findSysUserById(article.getAuthorId()).getNickname());
        }
        if (isBody) {
            articleVo.setBody(articleBodyService.findArticleBodyById(article.getBodyId()));
        }
        if (isCategory) {
            articleVo.setCategory(categoryService.findCategoryById(article.getCategoryId()));
        }
        return articleVo;
    }


}
