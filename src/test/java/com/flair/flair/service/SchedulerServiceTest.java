package com.flair.flair.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.flair.flair.model.EmailMessageTo;
import com.flair.flair.service.email.EmailService;
import com.flair.flair.service.email.SchedulerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {

  @InjectMocks private SchedulerService schedulerService;

  @Mock private EmailService emailService;

  @Test
  public void testIfSendEmailCalledOnce() {
    this.schedulerService.sendScheduledMail();

    verify(emailService, times(1)).sendMail(any());
  }

  @Test
  public void testIfArgumentIsCorrect() {
    this.schedulerService.sendScheduledMail();
    ArgumentCaptor<EmailMessageTo> captor = ArgumentCaptor.forClass(EmailMessageTo.class);
    verify(emailService).sendMail(captor.capture());

    EmailMessageTo value = captor.getValue();
    assertEquals("pawelsierzputowski3@gmail.com", value.getTo());
    assertEquals("It's automatically sent message", value.getSubject());
    assertEquals("Hi, it's 19 pm.", value.getText());
  }
}
