package com.parfait.study.simpleevent.service.event;

import com.parfait.study.simpleevent.model.SendableParameter;
import com.parfait.study.simpleevent.model.email.EmailTemplateType;
import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import com.parfait.study.simpleevent.service.email.EmailService;
import com.parfait.study.simpleevent.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import static com.parfait.study.simpleevent.service.member.AopAsyncEventMemberJoinService.AopAsyncMemberJoinedEvent;

@Component
public class AopAsyncMemberJoinedEventListener {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = AopAsyncMemberJoinedEvent.class)
    public void handle(AopAsyncMemberJoinedEvent event) {
        SendableParameter params = event.getSendableParameter();
        emailService.sendEmail(params.getEmail(), EmailTemplateType.JOIN);
        smsService.sendSms(params.getPhoneNo(), SmsTemplateType.JOIN);
    }
}
