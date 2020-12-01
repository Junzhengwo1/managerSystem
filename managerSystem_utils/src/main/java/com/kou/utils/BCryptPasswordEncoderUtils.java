package com.kou.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 对密码加密处理
 * @author JIAJUN KOU
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }
}
