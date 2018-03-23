package com.parfait.study.simpleevent.service.member;

import com.parfait.study.simpleevent.aop.PublishEvent;
import com.parfait.study.simpleevent.aop.PublishEvents;
import com.parfait.study.simpleevent.mapper.MemberMapper;
import com.parfait.study.simpleevent.model.email.SendEmailRequestEvent;
import com.parfait.study.simpleevent.model.member.Member;
import com.parfait.study.simpleevent.model.sms.SendSmsRequestEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("distributed-aop-async-event")
@Service
@Transactional
public class DistributedAopAsyncEventMemberJoinService implements MemberJoinService {

    private static final String emailEventParams =
            "#{T(com.parfait.study.simpleevent.model.email.SendEmailRequest).create(email, T(com.parfait.study.simpleevent.model.email.EmailTemplateType).JOIN)}";
    private static final String smsEventParams =
            "#{T(com.parfait.study.simpleevent.model.sms.SendSmsRequest).create(phoneNo, T(com.parfait.study.simpleevent.model.sms.SmsTemplateType).JOIN)}";

    @Autowired
    private MemberMapper memberMapper;

    @PublishEvents({
            @PublishEvent(eventType = SendEmailRequestEvent.class, params = emailEventParams),
            @PublishEvent(eventType = SendSmsRequestEvent.class, params = smsEventParams),
    })
    public Member join(Member member) {

        memberMapper.insert(member);
        return member;
    }
}
