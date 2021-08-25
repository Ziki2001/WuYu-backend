package com.ziki.holidaywork.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Pmt {
    public final static String SALT = "123abc";
    public static String REG_CODE;
    public static String STORE_PATH;

    @Value("${regCode}")
    public void setRegCode(String v) {
        REG_CODE = v;
    }
    @Value("${storePath}")
    public void setStorePath(String v) {
        STORE_PATH = v;
    }
}
