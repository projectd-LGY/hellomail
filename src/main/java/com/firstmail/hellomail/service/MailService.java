package com.firstmail.hellomail.service;

import com.firstmail.hellomail.dao.RepairDataDao;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService {

    //slf4j 日志
    private final Logger logger=LoggerFactory.getLogger(this.getClass());

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    RepairDataDao repairDataDao;



    /**
     * 发送
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to,
                               String subject,
                               String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        message.setFrom(from);

        javaMailSender.send(message);
    }

    /**
     * 发送html邮件
     * @param
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public void sendHtmlMail( String to,String subject, String content)  {

//        logger.info("发送静态邮件开始：{}，{}，{}",to,subject,content);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);

//            logger.info("succeed!");
        } catch (MessagingException e) {
//            logger.error("fail！",e);

        }




    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePathList
     * @throws MessagingException
     */
    public void sendAttachmentMail(String to,
                                   String subject,
                                   String content,
                                   String[] filePathList) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        //filePathList 附件文件路径
        for (String filePath: filePathList) {
            System.out.println(filePath);

            FileSystemResource fileSystemResource = new FileSystemResource(new File(filePath));
            String fileName = fileSystemResource.getFilename();
            helper.addAttachment(fileName, fileSystemResource);
        }

        javaMailSender.send(message);
    }

    /**
     * 发送带图片的邮件  是在html中插入图片
     * @param to
     * @param subject
     * @param content
     * @param srcPath
     * @param srcId
     * @throws MessagingException
     */
    public void sendHtmlInlinePhotoMail(String to,
                                        String subject,
                                        String content,
                                        String srcPath,
                                        String srcId) throws MessagingException {
        logger.info("发送静态邮件开始：{}，{}，{}，{}，{}",to,subject,content,srcPath,srcId);


        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            //srcPath为本地图片路径
            FileSystemResource fileSystemResource = new FileSystemResource(new File(srcPath));
            helper.addInline(srcId, fileSystemResource);

            javaMailSender.send(message);
            logger.info("succed!");
        } catch (MessagingException e) {
            logger.error("fail",e);
        }

    }


}
