package com.parfait.study.simpleevent.service.email;

import com.parfait.study.simpleevent.model.SendableParameter;
import com.parfait.study.simpleevent.service.member.DistributedAopAsyncEventMemberJoinService.DistributedAopAsyncMemberJoinedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class EmailEventListener {

    @Autowired
    private EmailService emailService;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT, classes = DistributedAopAsyncMemberJoinedEvent.class)
    public void handleMemberJoinedEvent(DistributedAopAsyncMemberJoinedEvent event) {
        SendableParameter parameter = event.getValue();
        emailService.sendEmail(parameter.getEmail(), parameter.getEmailTemplateType());
    }
}
