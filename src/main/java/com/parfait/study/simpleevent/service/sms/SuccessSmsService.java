package com.parfait.study.simpleevent.service.sms;

import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("!fail")
@Slf4j
@Service
public class SuccessSmsService implements SmsService {

    public void sendSms(String phoneNo, SmsTemplateType type) {
        log.info("send {} sms to {}", type, phoneNo);
    }
}
