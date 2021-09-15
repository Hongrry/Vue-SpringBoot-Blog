package com.hongrry.blog.admin.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongrry.blog.admin.dao.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Hongrry
 * @create 2021-09-15 14:21
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT * FROM ms_permission WHERE id IN (SELECT permission_id FROM ms_admin_permission WHERE admin_id=#{adminId})")
    List<Permission> findPermissionsByAdminId(Long adminId);
}
