package com.hongrry.blog.dao.vo.params;

import com.hongrry.blog.dao.pojo.ArticleBody;
import com.hongrry.blog.dao.vo.CategoryVo;
import com.hongrry.blog.dao.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author Hongrry
 * @create 2021-09-03 10:51
 */
@Data
public class ArticleParam {
    private ArticleBody body;
    private CategoryVo category;
    private String summary;
    private List<TagVo> tags;
    private String title;
}
