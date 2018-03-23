package com.parfait.study.simpleevent.service.email;

import com.parfait.study.simpleevent.model.email.SendEmailRequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EmailEventService {

    @Autowired
    private EmailService emailService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = SendEmailRequestEvent.class)
    public void sendEmail(SendEmailRequestEvent event) {
        emailService.sendEmail(event.getRequest().getEmail(), event.getRequest().getType());
    }
}
