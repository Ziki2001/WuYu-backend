package com.ziki.holidaywork.controller;

import com.ziki.holidaywork.entity.JsonResult;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 标记这个对象为RestController的异常处理类
@RestControllerAdvice
public class ExceptionAdvice {

    // 出现NullPointerException的时候，会跑到这里，其他就跑到下面的那个方法
    @ExceptionHandler(NullPointerException.class)
    public JsonResult nullErr() {
        return JsonResult.getInstance(401, "账号或密码错误了", null);
    }

    // 标记这个方法捕获Exception类型的异常（也就是所有异常）
    // 控制器出现异常时，会转到这里进行处理
    // 简化代码和架构
    @ExceptionHandler(Exception.class)
    public JsonResult otherError(Exception e) {
        // 参数类型如果和ExceptionHandler里的匹配，就能拿到控制器触发的异常了
        // 这里的写法和控制器一致
        return JsonResult.getInstance(500, "服务器错误", e.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public JsonResult duplicatekeyError() {

        return JsonResult.getInstance(401, "该用户名已存在", null);
    }
}