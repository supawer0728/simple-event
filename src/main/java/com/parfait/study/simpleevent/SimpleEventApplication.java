package com.parfait.study.simpleevent;

import com.parfait.study.simpleevent.mapper.MemberMapper;
import com.parfait.study.simpleevent.model.member.Member;
import com.parfait.study.simpleevent.service.member.MemberJoinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@Profile({"simple", "advanced", "event", "async-event", "aop-async-event", "distributed-aop-async-event"})
@EnableAsync(proxyTargetClass = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@SpringBootApplication
public class SimpleEventApplication implements CommandLineRunner {

    @Autowired
    private MemberJoinService memberJoinService;
    @Autowired
    private MemberMapper memberMapper;

    public static void main(String[] args) {
        SpringApplication.run(SimpleEventApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("{} would service", memberJoinService.getClass().getCanonicalName());
        try {
            Member member = Member.create("test", "test@test.com", "012-3456-7890");
            memberJoinService.join(member);
        } catch (Exception e) {
            log.error("{} was thrown", e.getClass().getCanonicalName());
        }
        log.info("member count : {}", memberMapper.count());
    }
}
