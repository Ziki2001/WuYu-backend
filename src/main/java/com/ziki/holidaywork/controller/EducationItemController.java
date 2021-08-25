package com.ziki.holidaywork.controller;

import com.ziki.holidaywork.dao.EducationItemDao;
import com.ziki.holidaywork.entity.EducationItem;
import com.ziki.holidaywork.entity.JsonResult;
import com.ziki.holidaywork.entity.Pmt;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class EducationItemController {
    @Resource
    private EducationItemDao educationItemDao;

    /**
     * 用户上传图片及相关信息到系统中
     *
     * @param date
     * @param type
     * @param content
     * @param file
     * @return
     * @throws IOException
     */

    @PostMapping("/api/educationItem/createNewItem")
    public JsonResult manager(String date, String type, String content, MultipartFile file) throws IOException {
        educationItemDao.addUserData(date, type, content);
        String fileName = type + "-" + content + "-" + date + ".jpeg";
        file.transferTo(Paths.get(Pmt.STORE_PATH + fileName));
        return JsonResult.getInstance(200, type + ": " + content + " 已成功录入", null);
    }

    @GetMapping("/api/getAllItems")
    public List<EducationItem> getItemList() {
        return educationItemDao.getList();
    }

    @DeleteMapping("/api/educationItem/:admin")
    public JsonResult delete(@RequestBody EducationItem input) throws IOException {
        educationItemDao.deleteEducationItem(input.type, input.content, input.date);
        String fileName = input.type + "-" + input.content + "-" + input.date + ".jpeg";
        File delFile = new File(Pmt.STORE_PATH + fileName);
        delFile.delete();
        return JsonResult.getInstance(200, "信息删除成功", null);
    }

    @GetMapping("/api/educationItem/image/2021")
    public ResponseEntity<org.springframework.core.io.Resource> sendImage(String type, String content, String date) throws MalformedURLException, UnsupportedEncodingException {
        String fileName = type + "-" + content + "-" + date;
        Path path = Paths.get(Pmt.STORE_PATH + fileName);
        UrlResource urlResource = new UrlResource(path.toUri());
        return ResponseEntity.ok()
                .header("Content-Type", "image/jpeg")
                .header("Content-Disposition", "inline;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8))
                .body(urlResource);
    }

    @PutMapping("/api/educationItem/:admin")
    public JsonResult updateEducationItem(String newType, String newContent, String newDate, String oldType,
                                          String oldContent, String oldDate) throws IOException {
        String originalFileName = oldType + "-" + oldContent + "-" + oldDate + ".jpeg";
        String newFileName = newType + "-" + newContent + "-" + newDate + ".jpeg";
        educationItemDao.updateEducationItem(newType, newContent, newDate, oldType, oldContent, oldDate);
        File f = new File(Pmt.STORE_PATH + originalFileName);
        f.renameTo(new File(Pmt.STORE_PATH + newFileName));

        return JsonResult.getInstance(200, "信息修改成功", null);
    }

    @PutMapping("/api/educationItem/changeImg")
    public JsonResult changeEducationItemImg(String type, String content, String date, MultipartFile file) throws IOException {
        String fileName = type + "-" + content + "-" + date + ".jpeg";
        file.transferTo(Paths.get(Pmt.STORE_PATH + fileName));
        return JsonResult.getInstance(200, "图片修改成功", null);
    }
}
