package com.parfait.study.simpleevent.service.member;

import com.parfait.study.simpleevent.mapper.MemberMapper;
import com.parfait.study.simpleevent.model.member.Member;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Profile("async-event")
@Service
@Transactional
public class AsyncEventMemberJoinService implements ApplicationEventPublisherAware, MemberJoinService {

    @Autowired
    private MemberMapper memberMapper;
    private ApplicationEventPublisher eventPublisher;

    public Member join(Member member) {

        memberMapper.insert(member);
        eventPublisher.publishEvent(new AsyncMemberJoinedEvent(this, member)); // gray zone

        return member;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    public static class AsyncMemberJoinedEvent extends ApplicationEvent {

        @Getter
        private Member member;

        private AsyncMemberJoinedEvent(Object source, @NonNull Member member) {
            super(source);
            this.member = member;
        }
    }
}
