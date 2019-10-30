package com.firstmail.hellomail.controller;

import com.firstmail.hellomail.dao.ProductionDAO;
import com.firstmail.hellomail.dao.RepairDataDao;
import com.firstmail.hellomail.domain.ProductionData;
import com.firstmail.hellomail.domain.RepairData;
import com.firstmail.hellomail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import javax.mail.MessagingException;


@RestController
public class MailController {

    //resource与autowired都是注入
    @Autowired
    MailService mailService;

    @Autowired
    RepairDataDao repairDataDao;
    @Autowired
    ProductionDAO productionDAO;

    @Autowired
    TemplateEngine templateEngine;

    String to = "3174389797@qq.com";


    /**
     * 发送维修单图片任务
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/htmlmail")
    public String sendRepairPMail() throws MessagingException {

        String srcPath = "C:\\Users\\SKY\\Desktop\\黑马seckill\\电商项目秒杀系统实战（一）资料\\assets\\databaseTable.png";
        String srcId = "pic18";
        String content = "<html>" +
                "<body>" +
                "<h2>hello，这是Spring Boot发送的一封HTML内嵌图片的邮件</h2>" +
                "<img src=\'cid:"+ srcId +"\'></img>" +
                "</body></html>";

        mailService.sendHtmlInlinePhotoMail("3174389797@qq.com", "发送图片邮件", content, srcPath, srcId);

        return "secceed!";
    }

//    public String sendRepairMail(){
//
//        return "secceed!";
//    }


    /**
     * 发送维修单邮件
     * @return
     * @throws MessagingException
     */
    @RequestMapping("/repairmail")
    public String sendRepairMail() throws MessagingException {
        RepairData repairData = repairDataDao.getOne(1);

        Context context = new Context();
        context.setVariable("userid", repairData.getUserid());
        context.setVariable("createDate",repairData.getCreatedate());
        context.setVariable("clientMail",repairData.getClientmail());

        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(repairData.getClientmail(), "发送模板邮件", emailContent);
        System.out.println(repairData.toString());
        return "succedd!";
    }

    /**
     * 发送生产数据邮件
     * @return
     * @throws MessagingException
     */

    @RequestMapping("/productionmail")
    public String sendProductionMail() throws MessagingException {
        ProductionData productionData = productionDAO.getOne(1);

        Context context = new Context();
        context.setVariable("data1",productionData.getData1());
        context.setVariable("data2",productionData.getData2());
        context.setVariable("data3",productionData.getData3());

        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail(productionData.getClientmail(), "发送模板邮件", emailContent);
        System.out.println(productionData.toString());
        return "succedd!";
    }

    @RequestMapping("/findone/{id}")
    public RepairData findOne(@PathVariable("id") int id){
        RepairData one = repairDataDao.getOne(id);
        System.out.println(one.getClientmail());
        System.out.println(one.getCreatedate());

        return one;
    }

    @RequestMapping("/findpro/{id}")
    public ProductionData findpro(@PathVariable("id") int id){
        ProductionData one = productionDAO.getOne(id);
        return one;
    }

}
