package com.flair.flair.service;

import com.flair.flair.model.EmailMessageTo;
import com.flair.flair.service.email.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void testIfSendCalledOnce() {
        EmailMessageTo emailMessageTo = new EmailMessageTo();
        emailMessageTo.setTo("testemail@test.com");
        emailMessageTo.setSubject("Test Subject");
        emailMessageTo.setText("Test Text");
        emailService.sendMail(emailMessageTo);

        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    public void testIfArgumentIsCorrect() {
        EmailMessageTo emailMessageTo = new EmailMessageTo();
        emailMessageTo.setTo("testemail@test.com");
        emailMessageTo.setSubject("Test Subject");
        emailMessageTo.setText("Test Text");
        emailService.sendMail(emailMessageTo);

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);

        verify(javaMailSender).send(captor.capture());
        SimpleMailMessage value = captor.getValue();

        assertEquals(emailMessageTo.getTo(), Objects.requireNonNull(value.getTo())[0]);
        assertEquals(emailMessageTo.getSubject(), value.getSubject());
        assertEquals(emailMessageTo.getText(), value.getText());
    }

}