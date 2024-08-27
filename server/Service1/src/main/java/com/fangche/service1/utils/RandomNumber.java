package com.fangche.service1.utils;

public class RandomNumber {

    /**
     * 生成指定长度的随机数字符串
     * @param length 随机数字符串的长度
     * @return 随机数字符串
     */
    public static String generateRandomNumber(int length) {
        // 生成指定长度的随机数字符串
        StringBuilder randomNumber = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomDigit = (int) (Math.random() * 10);
            randomNumber.append(randomDigit);
        }
        return randomNumber.toString();
    }
}

