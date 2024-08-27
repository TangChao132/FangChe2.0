package com.fangche.service1.utils;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.Random;

/**
 * &#064;Description  将明文密码进行MD5加盐加密
 **/
public class SaltMD5Util {

    /**
     * &#064;Description  生成普通的MD5密码
     **/
    public static String MD5(String input) {
        MessageDigest md5 = null;
        try {
            // 生成普通的MD5密码
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return "check jdk";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = input.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * &#064;Description  生成盐和加盐后的MD5码，并将盐混入到MD5码中,对MD5密码进行加强
     **/
    public static String generateSaltPassword(String password) {
        Random random = new Random();
        //生成一个16位的随机数，也就是所谓的盐
        /*
          此处的盐也可以定义成一个系统复杂点的常量,而不是非要靠靠随机数随机出来 两种方式任选其一 例如下面这行代码:
          盐加密 ：SALT的字符串是随意打的，目的是把MD5加密后的再次加密变得复杂
          public static final String SALT = "fskdhfiuhjfshfjhsad4354%@!@#%3";
         */
        StringBuilder stringBuilder = new StringBuilder(16);
        stringBuilder.append(random.nextInt(99999999)).append(random.nextInt(99999999));
        int len = stringBuilder.length();
        if (len < 16) {
            stringBuilder.append("0".repeat(Math.max(0, 16 - len)));
        }
        // 生成盐
        String salt = stringBuilder.toString();
        //将盐加到明文中，并生成新的MD5码
        password = md5Hex(password + salt);
        //将盐混到新生成的MD5码中，之所以这样做是为了后期更方便的校验明文和秘文，也可以不用这么做，不过要将盐单独存下来，不推荐这种方式
        char[] cs = new char[48];
        for (int i = 0; i < 48; i += 3) {
            cs[i] = password.charAt(i / 3 * 2);
            char c = salt.charAt(i / 3);
            cs[i + 1] = c;
            cs[i + 2] = password.charAt(i / 3 * 2 + 1);
        }
        return new String(cs);
    }

    /**
     * &#064;Description  验证明文和加盐后的MD5码是否匹配
     **/
    public static boolean verifySaltPassword(String password, String md5) {
        //先从MD5码中取出之前加的盐和加盐后生成的MD5码
        char[] cs1 = new char[32];
        char[] cs2 = new char[16];
        for (int i = 0; i < 48; i += 3) {
            cs1[i / 3 * 2] = md5.charAt(i);
            cs1[i / 3 * 2 + 1] = md5.charAt(i + 2);
            cs2[i / 3] = md5.charAt(i + 1);
        }
        String salt = new String(cs2);
        //比较二者是否相同
        return Objects.equals(md5Hex(password + salt), new String(cs1));
    }

    /**
     * &#064;Description  生成MD5密码
     **/
    private static String md5Hex(String src) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = md5.digest(src.getBytes());
            return new String(new Hex().encode(bs));
        } catch (Exception e) {
            return null;
        }
    }

}



