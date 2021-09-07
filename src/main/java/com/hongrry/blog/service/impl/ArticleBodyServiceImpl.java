package com.hongrry.blog.service.impl;

import com.hongrry.blog.dao.mapper.ArticleBodyMapper;
import com.hongrry.blog.dao.pojo.ArticleBody;
import com.hongrry.blog.dao.vo.ArticleBodyVo;
import com.hongrry.blog.service.ArticleBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hongrry
 * @create 2021-08-31 13:10
 */
@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {
    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Override
    public ArticleBodyVo findArticleBodyById(Long id) {
        ArticleBody articleBody = articleBodyMapper.selectById(id);
        return copy(articleBody);
    }

    private ArticleBodyVo copy(ArticleBody articleBody) {
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return articleBodyVo;
    }
}
