package com.hongrry.blog.dao.vo.params;

import lombok.Data;

/**
 * @author Hongrry
 * @create 2021-08-29 13:13
 */
@Data
public class PageParams {
    private int page = 1;
    private int pageSize = 10;
    private Long categoryId;
    private String sort = "desc";
    private Long tagId;
    private String year;

    private String month;

    public String getMonth() {
        if (this.month != null && this.month.length() == 1) {
            return "0" + this.month;
        }
        return this.month;
    }
}
