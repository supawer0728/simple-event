package com.parfait.study.simpleevent.service.email;

import com.parfait.study.simpleevent.model.email.EmailTemplateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    public void sendEmail(String email, EmailTemplateType type) {
        log.info("send {} email to {}", type, email);
    }
}
