package com.alzz.demo.service;

import com.alibaba.excel.EasyExcel;
import com.alzz.demo.domain.User;
import com.alzz.demo.repository.UserMapper;
import com.alzz.demo.utils.SCPClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UploadService
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/15 16:27
 */
@Service
public class UploadService {

    @Autowired
    SCPClientUtils sCPClientUtils;
    @Autowired
    UserMapper userMapper;

    public void fileUpload(MultipartFile file) {
        String filePath = new SimpleDateFormat("/YYYY/MM/").format(new Date());
        try {
            String path = sCPClientUtils.uploadFile(file.getBytes(), "/fileUpload"+filePath, file.getOriginalFilename());
            System.out.println(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<User> users = userMapper.selectByAll(null);
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("今晚打老虎", "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), User.class).sheet("模板").doWrite(users);
    }
}
