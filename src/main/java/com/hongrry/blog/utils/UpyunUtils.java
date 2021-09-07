package com.hongrry.blog.utils;

import com.UpYun;
import com.upyun.Params;
import com.upyun.RestManager;
import com.upyun.UpException;

import com.upyun.UpYunUtils;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hongrry
 * @create 2021-09-04 10:52
 */
@Component
public class UpyunUtils {
    private static RestManager restManager;
    public static final String BASE_ACCESS_PATH = "https:cloud.miooim.cn/static/";
    private static final String BASE_UPLOAD_PATH = "/static/";

    @Autowired
    public void setRestManager(RestManager restManager) {
        UpyunUtils.restManager = restManager;
    }

    public static boolean uploadFile(String fileName, byte[] data) throws UpException, IOException {
        Map<String, String> params = new HashMap<>(1);
        // 设置待上传文件的 Content-MD5 值
        // 如果又拍云服务端收到的文件MD5值与用户设置的不一致，将回报 406 NotAcceptable 错误
        params.put(Params.CONTENT_MD5, UpYunUtils.md5(data));
        Response response = restManager.writeFile(BASE_UPLOAD_PATH + fileName, data, null);
        return response.isSuccessful();
    }
}
