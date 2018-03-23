package com.parfait.study.simpleevent.service.sms;

import com.parfait.study.simpleevent.model.sms.SendSmsRequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class SmsEventService {

    @Autowired
    private SmsService smsEventService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = SendSmsRequestEvent.class)
    public void sendEmail(SendSmsRequestEvent event) {
        smsEventService.sendSms(event.getRequest().getPhoneNo(), event.getRequest().getType());
    }
}
