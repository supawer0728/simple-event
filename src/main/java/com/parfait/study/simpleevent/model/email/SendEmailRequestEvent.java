package com.parfait.study.simpleevent.model.email;

import lombok.Data;
import lombok.NonNull;

@Data
public class SendEmailRequestEvent {
    @NonNull
    private SendEmailRequest request;
}
