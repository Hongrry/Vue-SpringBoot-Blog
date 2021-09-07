package com.hongrry.blog.dao.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hongrry.blog.dao.pojo.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Hongrry
 * @create 2021-08-29 13:06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户名查询用户
     *
     * @param account 用户名
     * @return SysUser
     */
    SysUser findUserByAccount(String account);


}
