package com.parfait.study.simpleevent.service.sms;

import com.parfait.study.simpleevent.model.sms.SmsTemplateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {

    public void sendSms(String phoneNo, SmsTemplateType type) {
        log.info("send {} sms to {}", type, phoneNo);
    }
}
