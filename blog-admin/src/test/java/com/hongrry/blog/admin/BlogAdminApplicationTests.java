package com.hongrry.blog.admin;

import com.hongrry.blog.admin.dao.mapper.PermissionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAdminApplicationTests {
    @Autowired
    PermissionMapper permissionMapper;

    @Test
    void contextLoads() {
        System.out.println(permissionMapper.findPermissionsByAdminId(1L));
    }

}
