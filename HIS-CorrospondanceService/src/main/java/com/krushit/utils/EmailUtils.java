package com.krushit.utils;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailMessage(String toMail, String subject, String body, File file) throws Exception {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setSubject(subject);
            helper.setTo(toMail);
            helper.setText(body, true);
            helper.addAttachment(file.getName(), file);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
