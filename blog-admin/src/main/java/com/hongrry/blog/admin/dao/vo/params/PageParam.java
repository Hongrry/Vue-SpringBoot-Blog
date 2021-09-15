package com.hongrry.blog.admin.dao.vo.params;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-09-15 14:15
 */
@Data
public class PageParam {

    private Integer currentPage;

    private Integer pageSize;

    private String queryString;
}
