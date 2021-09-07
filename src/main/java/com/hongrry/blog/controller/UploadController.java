package com.hongrry.blog.controller;

import com.hongrry.blog.dao.vo.ErrorCode;
import com.hongrry.blog.dao.vo.Result;
import com.hongrry.blog.utils.UpyunUtils;
import com.upyun.UpException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Hongrry
 * @create 2021-09-04 10:47
 */
@RestController
@RequestMapping("upload")
public class UploadController {
    @PostMapping
    public Result upload(@RequestParam("image") MultipartFile file) throws IOException, UpException {
        String fileName = UUID.randomUUID().toString() + '.' +
                StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        boolean success = UpyunUtils.uploadFile(fileName, file.getBytes());
        if (success) {
            return Result.success(UpyunUtils.BASE_ACCESS_PATH + fileName);
        } else {
            return Result.fail(20001, "上传失败");
        }
    }
}
