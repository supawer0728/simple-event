package com.parfait.study.simpleevent.service.member;

import com.parfait.study.simpleevent.mapper.MemberMapper;
import com.parfait.study.simpleevent.model.email.EmailTemplateType;
import com.parfait.study.simpleevent.model.member.Member;
import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import com.parfait.study.simpleevent.service.email.EmailService;
import com.parfait.study.simpleevent.service.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Profile("advanced")
@Service
@Transactional
public class AdvancedMemberJoinService implements MemberJoinService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;

    public Member join(Member member) {

        memberMapper.insert(member);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void afterCommit() {
                emailService.sendEmail(member.getEmail(), EmailTemplateType.JOIN);
                smsService.sendSms(member.getPhoneNo(), SmsTemplateType.JOIN);
            }
        });

        return member;
    }
}
