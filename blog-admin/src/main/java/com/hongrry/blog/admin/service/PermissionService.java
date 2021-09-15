package com.hongrry.blog.admin.service;

import com.hongrry.blog.admin.dao.pojo.Permission;
import com.hongrry.blog.admin.dao.vo.Result;
import com.hongrry.blog.admin.dao.vo.params.PageParam;

/**
 * @author Hongrry
 * @create 2021-09-15 14:18
 */
public interface PermissionService {
    /**
     * 分页查询权限
     *
     * @param pageParam
     * @return
     */
    Result listPermission(PageParam pageParam);

    /**
     * 新增权限
     *
     * @param permission
     * @return
     */
    Result addPermission(Permission permission);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    Result deletePermission(Long id);

    /**
     * 更新权限
     *
     * @param permission
     * @return
     */
    Result updatePermission(Permission permission);
}
