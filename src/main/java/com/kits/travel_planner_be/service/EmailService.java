package com.kits.travel_planner_be.service;

import com.kits.travel_planner_be.model.EmailDetails;

public interface EmailService {
    boolean sendMailResetPassword(String newPassword, String recipient);
    String sendMailWithAttachment(EmailDetails details);
}
