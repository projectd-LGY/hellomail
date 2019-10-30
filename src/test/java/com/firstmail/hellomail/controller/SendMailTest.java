package com.firstmail.hellomail.controller;

import com.firstmail.hellomail.dao.RepairDataDao;
import com.firstmail.hellomail.domain.RepairData;
import com.firstmail.hellomail.service.MailService;
import com.firstmail.hellomail.service.RepairDataService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SendMailTest {

    @Autowired
    MailController mailController;
    @Autowired
    RepairDataDao repairDataDao;
    @Autowired
    RepairDataService repairDataService;
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    MailService mailService;

    String to = "3174389797@qq.com";

    @Test
    public void sendRepairMail() throws MessagingException {


        Context context = new Context();
//        context.setVariable("id", one.getId());


        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(to, "发送模板邮件", emailContent);
    }

    @Test
    public Object get(){
        RepairData one = repairDataDao.getOne(1);
        return one;
    }
}
