package com.hongrry.blog.admin.dao.vo;

import lombok.Data;

import java.util.List;

/**
 * @author Hongrry
 * @create 2021-09-15 14:28
 */
@Data
public class PageResult<T> {
    private List<T> list;
    private Long total;
}
