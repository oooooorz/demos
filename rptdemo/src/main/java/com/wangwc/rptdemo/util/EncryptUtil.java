package com.wangwc.rptdemo.util;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 加密工具类
 */
@Component
public class EncryptUtil {

    @Autowired
    private StringEncryptor encryptor;

    public String encrypt(String str){
        String encStr = encryptor.encrypt(str);
        System.out.println(encStr);
        return encStr;
    }
}
