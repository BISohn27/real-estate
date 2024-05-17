package com.message.messagequeue.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberSignUpDto {

    private final String username;
    private final String password;
}
