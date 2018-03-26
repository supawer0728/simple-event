package com.parfait.study.simpleevent.service.member;

import com.parfait.study.simpleevent.aop.PublishEvent;
import com.parfait.study.simpleevent.mapper.MemberMapper;
import com.parfait.study.simpleevent.model.SendableParameter;
import com.parfait.study.simpleevent.model.event.EventHoldingValue;
import com.parfait.study.simpleevent.model.member.Member;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("distributed-aop-async-event")
@Service
@Transactional
public class DistributedAopAsyncEventMemberJoinService implements MemberJoinService {

    @Autowired
    private MemberMapper memberMapper;

    @PublishEvent(eventType = DistributedAopAsyncMemberJoinedEvent.class, params = "#{T(com.parfait.study.simpleevent.model.SendableParameter).create(email, phoneNo)}")
    public Member join(Member member) {

        memberMapper.insert(member);
        return member;
    }

    public static class DistributedAopAsyncMemberJoinedEvent implements EventHoldingValue<SendableParameter> {

        @Getter
        private SendableParameter value;

        public DistributedAopAsyncMemberJoinedEvent(@NonNull SendableParameter sendableParameter) {
            this.value = sendableParameter;
        }
    }
}
