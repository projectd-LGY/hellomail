package com.firstmail.hellomail.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.naming.Name;
import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
public class ServiceTest {
    @Resource
    MailService mailService;


    @Resource
    TemplateEngine templateEngine;

    String to = "3174389797@qq.com";

    @Test
    public void sendSimpleMailTest(){

        mailService.sendSimpleMail(to, "发送文本邮件",
                    "hello，这是Spring Boot发送的一封文本邮件!");
    }

    @Test
    public void sendHtmlEmailTest() throws MessagingException {
        String content = "<html>" +
                "<body>" +
                "<h1 style=\"" + "color:red;" + "\">hello，这是Spring Boot发送的一封HTML邮件</h1>" +
                "</body></html>";

        mailService.sendHtmlMail(to, "发送HTML邮件", content);
    }

    @Test
    public void sendAttachmentEmailTest() throws MessagingException {
        String[] filePathList = new String[1];
        filePathList[0] = "\\Users\\SKY\\Desktop\\SpringBoot-Course-master";
//        filePathList[1] = "/Users/qufei/Documents/study/SpringBoot-WebApi/chapter5.zip";

        mailService.sendAttachmentMail(to, "发送附件邮件",
                "hello，这是Spring Boot发送的一封附件邮件!",
                filePathList);
    }

    @Test
    public void sendHtmlInlinePhotoMailTest() throws MessagingException {
        String srcPath = "C:\\Users\\SKY\\Desktop\\黑马seckill\\电商项目秒杀系统实战（一）资料\\assets\\databaseTable.png";
        String srcId = "pic18";
        String content = "<html>" +
                "<body>" +
                "<h2>hello，这是Spring Boot发送的一封HTML内嵌图片的邮件</h2>" +
                "<img src=\'cid:"+ srcId +"\'></img>" +
                "</body></html>";

        mailService.sendHtmlInlinePhotoMail(to, "发送图片邮件", content, srcPath, srcId);
    }

    @Test
    public void testTemplateEmailTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("username", "hehe");
        context.setVariable("createTime","2019");
        context.setVariable("adress","*******");
        context.setVariable("id", "2667395");
        context.setVariable("time", DateFormat.getDateInstance().toString());


        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(to, "发送模板邮件", emailContent);
    }
}
