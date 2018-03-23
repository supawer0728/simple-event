package com.parfait.study.simpleevent.service.event;

import com.parfait.study.simpleevent.model.email.EmailTemplateType;
import com.parfait.study.simpleevent.model.member.Member;
import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import com.parfait.study.simpleevent.service.email.EmailService;
import com.parfait.study.simpleevent.service.member.EventMemberJoinService;
import com.parfait.study.simpleevent.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MemberJoinedEventListener {

    @Autowired
    private SmsService smsService;
    @Autowired
    private EmailService emailService;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = EventMemberJoinService.MemberJoinedEvent.class)
    public void handle(EventMemberJoinService.MemberJoinedEvent event) {
        Member member = event.getMember();
        emailService.sendEmail(member.getEmail(), EmailTemplateType.JOIN);
        smsService.sendSms(member.getPhoneNo(), SmsTemplateType.JOIN);
    }
}
