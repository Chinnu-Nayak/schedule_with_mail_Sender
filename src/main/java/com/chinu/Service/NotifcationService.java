package com.chinu.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Service
public class NotifcationService {
    @Autowired
    private ReportService reportService;
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;
    @Value("${report.send.email.toList}")
    private  String toEmails;

    public String sendDailyReport() throws MessagingException, IOException {
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(toEmails.split(","));

        mimeMessageHelper.setText("hii user\nplease find the attachment for today's order recieved!!!!!!");
        mimeMessageHelper.setSubject("list of orders"+new Date().getTime());

//        FileSystemResource f=new FileSystemResource(new File(emailDto.getAttachment()));

        byte[] bytes = reportService.generateExcelFile();
        ByteArrayResource content=new ByteArrayResource(bytes);

        mimeMessageHelper.addAttachment(new Date().getTime()+
                "_orders.xlsx",content);

        javaMailSender.send(mimeMessage);
        return "file sent successfully ";

    }

}
