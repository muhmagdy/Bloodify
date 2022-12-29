package com.bloodify.backend.AccountManagement.services.classes;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    private final String fromAddress = "bloodify.egypt@gmail.com";
    private final String senderName = "Bloodify";

    public void sendPasswordResetEmail(String toAddress, String code) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset";
        String content = "Dear user,<br>"
                + "Please enter the code below in application to reset your password:<br>"
                + "<h3> [[code]] </h3>"
                + "Please notice that the code is only valid for 30 minutes.<br>"
                + "Thank you,<br>"
                + "Bloodify.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        content = content.replace("[[code]]", code);
        helper.setText(content, true);
        mailSender.send(message);
    }
}
