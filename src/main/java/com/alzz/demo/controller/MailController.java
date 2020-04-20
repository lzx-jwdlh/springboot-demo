package com.alzz.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.common.ResponseBean;
import com.alzz.demo.constant.Constants;
import com.alzz.demo.domain.Mail;
import com.alzz.demo.service.MailService;
import com.alzz.demo.utils.ApplicationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailService mailService;

    /**
     * 发送注册验证码
     * @param mail
     * @return 验证码
     * @throws Exception
     */
    @PostMapping("/sendTemplateMail")
    public ResponseBean<String> sendTemplateMail(@RequestBody JSONObject params) throws Exception {
        String identifyingCode = ApplicationUtils.getNumStringRandom(6);
        Mail mail = new Mail();
        mail.setSubject("欢迎注册今晚打打老虎");
        mail.setTemplateName(Constants.REGISTER_TEMPLATE);
        String[] to = params.getJSONArray("to").toArray(new String[0]);
        mail.setTo(to);
        Map<String,String> map = new HashMap<>();
        map.put("identifyingCode",identifyingCode);
        map.put("to",to[0]);
        mail.setTemplateModel(map);
        mailService.sendTemplateMail(mail);
        return new ResponseBean(identifyingCode);
    }

    @PostMapping("/sendAttachmentsMail")
    public ResponseBean<String> sendAttachmentsMail(Mail mail, HttpServletRequest request) throws Exception {
        mail.setSubject("测试附件");
        mailService.sendAttachmentsMail(mail, request);
        return new ResponseBean();
    }
}
