package com.fangche.service2.Utility;

import org.bytedeco.javacv.FFmpegFrameGrabber;

public class VideoUtils {
    public static double getVideoDurationInSeconds(String filePath) {
        try (FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(filePath)) {
            grabber.start();
            return grabber.getLengthInTime() / 1000.0; // getLengthInTime()返回毫秒数，转换为秒
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // 表示获取失败
        }
    }
}
