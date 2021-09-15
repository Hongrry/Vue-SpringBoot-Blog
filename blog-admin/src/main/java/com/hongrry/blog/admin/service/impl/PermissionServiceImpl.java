package com.hongrry.blog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hongrry.blog.admin.dao.mapper.PermissionMapper;
import com.hongrry.blog.admin.dao.pojo.Permission;
import com.hongrry.blog.admin.dao.vo.PageResult;
import com.hongrry.blog.admin.dao.vo.Result;
import com.hongrry.blog.admin.dao.vo.params.PageParam;
import com.hongrry.blog.admin.service.PermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Hongrry
 * @create 2021-09-15 14:19
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Result listPermission(PageParam pageParam) {
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> queryWrapper = null;
        if (StringUtils.isNotBlank(pageParam.getQueryString())) {
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.like(Permission::getName, pageParam.getQueryString());
        }
        Page<Permission> permissionPage = permissionMapper.selectPage(page, queryWrapper);
        PageResult<Permission> permissionPageResult = new PageResult<>();
        permissionPageResult.setList(permissionPage.getRecords());
        permissionPageResult.setTotal(permissionPage.getTotal());

        return Result.success(permissionPageResult);
    }

    @Override
    public Result addPermission(Permission permission) {
        if (permission.getName() == null) {
            return Result.fail(403, "error");
        }
        permissionMapper.insert(permission);
        return Result.success(null);
    }

    @Override
    public Result deletePermission(Long id) {
        permissionMapper.deleteById(id);
        return Result.success(null);
    }

    @Override
    public Result updatePermission(Permission permission) {
        permissionMapper.updateById(permission);
        return Result.success(null);
    }
}
