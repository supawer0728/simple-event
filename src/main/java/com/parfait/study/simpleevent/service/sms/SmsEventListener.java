package com.parfait.study.simpleevent.service.sms;

import com.parfait.study.simpleevent.model.SendableParameter;
import com.parfait.study.simpleevent.service.member.DistributedAopAsyncEventMemberJoinService.DistributedAopAsyncMemberJoinedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class SmsEventListener {

    @Autowired
    private SmsService smsEventService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = DistributedAopAsyncMemberJoinedEvent.class)
    public void sendEmail(DistributedAopAsyncMemberJoinedEvent event) {
        SendableParameter request = event.getValue();
        smsEventService.sendSms(request.getPhoneNo(), request.getSmsTemplateType());
    }
}
