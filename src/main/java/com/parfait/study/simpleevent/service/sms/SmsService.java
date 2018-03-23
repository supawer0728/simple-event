package com.parfait.study.simpleevent.service.sms;

import com.parfait.study.simpleevent.model.sms.SmsTemplateType;

public interface SmsService {

    void sendSms(String phoneNo, SmsTemplateType type);
}
