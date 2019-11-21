package com.training.salon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    @Value("${spring.mail.username}")
    private String username;

    private final JavaMailSender mailSender;

    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String emailTo, Long masterId){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject("Leave Comment");
        mailMessage.setText(" Leave comment about our master:\n" +
                "http://localhost:8080/user/master/" + masterId);

        mailSender.send(mailMessage);
    }
}
