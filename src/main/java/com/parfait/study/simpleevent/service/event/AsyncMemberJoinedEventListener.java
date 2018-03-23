package com.parfait.study.simpleevent.service.event;

import com.parfait.study.simpleevent.model.email.EmailTemplateType;
import com.parfait.study.simpleevent.model.member.Member;
import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import com.parfait.study.simpleevent.service.email.EmailService;
import com.parfait.study.simpleevent.service.member.AsyncEventMemberJoinService.AsyncMemberJoinedEvent;
import com.parfait.study.simpleevent.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class AsyncMemberJoinedEventListener {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = AsyncMemberJoinedEvent.class)
    public void handle(AsyncMemberJoinedEvent event) {
        Member member = event.getMember();
        emailService.sendEmail(member.getEmail(), EmailTemplateType.JOIN);
        smsService.sendSms(member.getPhoneNo(), SmsTemplateType.JOIN);
    }
}
