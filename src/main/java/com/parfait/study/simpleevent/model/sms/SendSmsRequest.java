package com.parfait.study.simpleevent.model.sms;

import lombok.Data;
import lombok.NonNull;

@Data
public class SendSmsRequest {
    private String phoneNo;
    private SmsTemplateType type;

    public static SendSmsRequest create(@NonNull String phoneNo, @NonNull SmsTemplateType type) {
        SendSmsRequest event = new SendSmsRequest();
        event.setPhoneNo(phoneNo);
        event.setType(type);
        return event;
    }
}
