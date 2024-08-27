package com.fangche.service2.Utility;

import java.io.File;
import java.io.IOException;

public class StaticResourcesUtil {

    public static final String VIDEO_IMAGE_UPLOAD_DIR = "/static/video/course/";

    public static File getVideoImagePath(String fileName) throws IOException {
        // 获取项目根目录路径
        String baseDir = System.getProperty("user.dir");
        File uploadDir = new File(baseDir, VIDEO_IMAGE_UPLOAD_DIR);

        // 检查并创建目录
        if (!uploadDir.exists()) {
            if (!uploadDir.mkdirs()) {
                throw new IOException("无法创建目录: " + uploadDir.getAbsolutePath());
            }
        }

        File filePath = new File(uploadDir, fileName);

        // 如果文件不存在，创建新文件
        if (!filePath.exists()) {
            if (!filePath.createNewFile()) {
                throw new IOException("无法创建文件: " + filePath.getAbsolutePath());
            }
        }

        return filePath;
    }
}
