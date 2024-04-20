package com.example.carbook.service.impl;

import com.example.carbook.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailServiceImpl implements EmailService {
    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final String carbookEmail;

    public EmailServiceImpl(TemplateEngine templateEngine,
                            JavaMailSender javaMailSender,
                            @Value("${mail.carbook}") String carbookEmail) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
        this.carbookEmail = carbookEmail;
    }

    @Override
    public void sendRegistrationEmail(String userEmail, String username) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

        try {
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(carbookEmail);
            mimeMessageHelper.setReplyTo(carbookEmail);
            mimeMessageHelper.setSubject("Welcome to CarBook!");
            mimeMessageHelper.setText(generateRegistrationEmailBody(username), true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRegistrationEmailBody(String username) {
        Context context = new Context();
        context.setVariable("username", username);

        return templateEngine.process("email/registration-email", context);
    }
}
