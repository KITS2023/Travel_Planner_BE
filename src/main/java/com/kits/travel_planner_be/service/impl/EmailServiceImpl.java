package com.kits.travel_planner_be.service.impl;

import com.kits.travel_planner_be.model.EmailDetails;
import com.kits.travel_planner_be.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public boolean sendMailResetPassword(String newPassword, String recipient) {

        // Try block to check for exceptions
        try {

            SimpleMailMessage mailMessage = new SimpleMailMessage();

            String subject = "Reset your password";
            String msgBody = "Hi," +
                    "\n\n You requested to reset the password for your Travel planner account with e-mail address (" +
                    recipient + ")." +
                    "\nThis is your new password: " +
                    newPassword +
                    "\n\n Thanks" +
                    "\n Travel planner team.";

            mailMessage.setFrom(sender);
            mailMessage.setTo(recipient);
            mailMessage.setText(msgBody);
            mailMessage.setSubject(subject);

            javaMailSender.send(mailMessage);
            return true;
        }

        catch (Exception e) {
            return false;
        }
    }

    // To send an email with attachment
    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage
                = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper
                    = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody());
            mimeMessageHelper.setSubject(
                    details.getSubject());

            // Adding the attachment
            FileSystemResource file
                    = new FileSystemResource(
                    new File(details.getAttachment()));

            mimeMessageHelper.addAttachment(
                    file.getFilename(), file);

            // Sending the mail
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }
}
