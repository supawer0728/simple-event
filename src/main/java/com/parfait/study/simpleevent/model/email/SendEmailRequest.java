package com.parfait.study.simpleevent.model.email;

import lombok.Data;
import lombok.NonNull;

@Data
public class SendEmailRequest {
    private String email;
    private EmailTemplateType type;

    public static SendEmailRequest create(@NonNull String email, @NonNull EmailTemplateType type) {
        SendEmailRequest event = new SendEmailRequest();
        event.setEmail(email);
        event.setType(type);
        return event;
    }
}
