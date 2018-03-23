package com.parfait.study.simpleevent.model.sms;

import lombok.Data;
import lombok.NonNull;

@Data
public class SendSmsRequestEvent {
    @NonNull
    private SendSmsRequest request;
}
