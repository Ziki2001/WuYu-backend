package com.ziki.holidaywork.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.DigestUtils;

@NoArgsConstructor
@AllArgsConstructor
public class User {
    public Integer id;
    public String user;
    public String password;
    public String aca;
    public String name;
    public String clas;
    public String num;
    public String code;

    public boolean validPassword(String rawPassword) {
        return encodePassword(rawPassword).equals(password);
    }
    public static String encodePassword(String originPwd) {
        return DigestUtils.md5DigestAsHex((Pmt.SALT + originPwd).getBytes());
    }
}
