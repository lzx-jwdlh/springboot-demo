package com.alzz.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alzz.demo.core.excel.UploadDAO;
import com.alzz.demo.core.excel.UploadData;
import com.alzz.demo.core.excel.UploadDataListener;
import com.alzz.demo.service.UploadService;
import com.alzz.demo.utils.SCPClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName UploadController
 * @Description TODO
 * @Author lzx
 * @Date 2020/1/15 16:09
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @Autowired
    SCPClientUtils sCPClientUtils;
    @Autowired
    private UploadDAO uploadDAO;

    @RequestMapping("/upload")
    public String fileUpload(MultipartFile file) {

        System.out.println(file.getOriginalFilename());
        uploadService.fileUpload(file);
        return "";
    }


    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link UploadData}
     * <p>
     * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link UploadDataListener}
     * <p>
     * 3. 直接读即可
     */
    @PostMapping("/readExcel")
    @ResponseBody
    public String upload(MultipartFile file) throws IOException {
        long start = System.currentTimeMillis();
        EasyExcel.read(file.getInputStream(), UploadData.class, new UploadDataListener(uploadDAO)).sheet().doRead();
        System.out.println(System.currentTimeMillis()-start);
        return "success";
    }

    @GetMapping("/downloadExcel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        uploadService.downloadExcel(response);
    }
}
