package com.hongrry.blog;

import com.hongrry.blog.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogParentApplicationTests {
    @Autowired
    TagService tagService;

    @Test
    void contextLoads() {
        System.out.println(tagService.hots(2));
    }

}
