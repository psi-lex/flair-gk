package com.flair.flair.service.email;

import com.flair.flair.model.EmailMessageTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

    private final EmailService emailService;

    public SchedulerService(EmailService emailService) {
        this.emailService = emailService;
    }

    @Scheduled(cron = "0 0 19 * * *", zone = "Europe/Warsaw")
    public void sendScheduledMail() {
        EmailMessageTo emailMessageTo = new EmailMessageTo();
        emailMessageTo.setTo("pawelsierzputowski3@gmail.com");
        emailMessageTo.setSubject("It's automatically sent message");
        emailMessageTo.setText("Hi, it's 19 pm.");
        emailService.sendMail(
                emailMessageTo
        );
    }

}
