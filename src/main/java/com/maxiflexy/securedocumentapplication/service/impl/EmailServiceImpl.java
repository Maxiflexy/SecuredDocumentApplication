package com.maxiflexy.securedocumentapplication.service.impl;

import com.maxiflexy.securedocumentapplication.exception.ApiException;
import com.maxiflexy.securedocumentapplication.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.maxiflexy.securedocumentapplication.utils.EmailUtils.getEmailMessage;
import static com.maxiflexy.securedocumentapplication.utils.EmailUtils.getResetPasswordMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "New User Account Verification";
    public static final String PASSWORD_RESET_REQUEST = "Reset Password Request";
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountEmail(String name, String email, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getEmailMessage(name, host, token));
            javaMailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException("Unable to send email");
        }
    }

    @Override
    @Async
    public void sendPasswordEmail(String name, String email, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(PASSWORD_RESET_REQUEST);
            message.setFrom(fromEmail);
            message.setTo(email);
            message.setText(getResetPasswordMessage(name, host, token));
            javaMailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new ApiException("Unable to send email");
        }
    }
}
