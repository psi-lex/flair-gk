package com.flair.flair.service.email;

import com.flair.flair.model.EmailMessageTo;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private final JavaMailSender mailSender;

  public EmailService(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }

  public void sendMail(EmailMessageTo messageTo) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(messageTo.getTo());
    message.setSubject(messageTo.getSubject());
    message.setText(messageTo.getText());

    mailSender.send(message);
  }
}
