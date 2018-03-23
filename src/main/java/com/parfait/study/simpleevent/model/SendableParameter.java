package com.parfait.study.simpleevent.model;

import lombok.Data;

@Data
public class SendableParameter {

    private String email;
    private String phoneNo;

    public static SendableParameter create(String email, String phoneNo) {
        SendableParameter parameter = new SendableParameter();
        parameter.setEmail(email);
        parameter.setPhoneNo(phoneNo);
        return parameter;
    }
}
