package com.ziki.holidaywork.controller;


import com.ziki.holidaywork.dao.UserDao;
import com.ziki.holidaywork.entity.JsonResult;
import com.ziki.holidaywork.entity.Pmt;
import com.ziki.holidaywork.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;

// 路由还可以这样写
@RestController
public class UserController {
    @Resource
    private UserDao userDao;
//    @Resource
//    private UserDataDao userDataDao;
//    @GetMapping("/")
//    public JsonResult hello() {
//        return JsonResult.getInstance(1,"sadf", null);
//    }

    @PostMapping("/api/login")
    public JsonResult login(@RequestBody User input, HttpSession session) throws IOException {
        User admin = userDao.getUserByUsername(input.user);
        if (admin.validPassword(input.password)) {
            session.setAttribute("user",input.user);
            return JsonResult.getInstance(200,"登录成功",null);
        } else {
            return JsonResult.getInstance(401, "登录密码错误", null);
        }
    }
    @PostMapping("/api/register")
    public JsonResult register(@RequestBody User input) throws IOException {
        if(!input.code.equals(Pmt.REG_CODE)) {
            return JsonResult.getInstance(401, "邀请码错误",null);
        }else{
            userDao.addUser(input.user, User.encodePassword(input.password), input.aca, input.name, input.clas, input.num);
            return JsonResult.getInstance(200, "注册成功", null);
        }

    }
//    @GetMapping("/api/manager")
//    public JsonResult manager(@RequestParam("date") String date,
//                              @RequestParam("type") String type,
//                              @RequestParam("content") String content
//                              ) throws IOException {
//        userDataDao.addUserData(date,type,content);
//        return JsonResult.getInstance(200, "成功录入信息", null);
//    }
//    /**
//     * 向客户端响应一个文件内容
//     * @param localFilePath     本地文件路径
//     * @param name              文件响应重命名
//     * @return  响应实体
//     */
//    public ResponseEntity<org.springframework.core.io.Resource> sendFile(String localFilePath, String name) throws MalformedURLException, UnsupportedEncodingException {
//        Path path = Paths.get(localFilePath);
//        if (Files.isDirectory(path)) {
//            throw new IllegalArgumentException("无法直接下载文件夹");
//        }
//        UrlResource urlResource = new UrlResource(path.toUri());
//        return ResponseEntity.ok()
//                .header("Content-Type", FileUtils.getContentType(name))
//                .header("Content-Disposition", "inline;filename="+ URLEncoder.encode(name, "utf-8"))
//                .body(urlResource);
//    }

//    @GetMapping("/test")
//    public JsonResult valid(HttpSession session) {
//        Object user = session.getAttribute("user");
//        if (user == null) {
//            return JsonResult.getInstance(400, "未登录", null);
//        } else {
//            return JsonResult.getInstance(1, "OK", user);
//        }
//    }

//    @GetMapping("/err")
//    public JsonResult testErr() throws Exception {
//        // 换个数组越界的
//        int[] arr = new int[1];
//        arr[3] = 1;
//        return JsonResult.getInstance();
//    }
}
