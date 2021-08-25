package com.ziki.holidaywork.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class JsonResult {
    public int code = 1;
    public String msg = "OK";
    public Object data = null;

    public static JsonResult getInstance( ) {
        return new JsonResult();
    }

    public static JsonResult getInstance(int code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }
}
