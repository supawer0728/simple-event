package com.parfait.study.simpleevent.model.member;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String name;
    private String email;
    private String phoneNo;

    public static Member create(@NonNull String name, @NonNull String email, @NonNull String phoneNo) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNo(phoneNo);

        return member;
    }
}
