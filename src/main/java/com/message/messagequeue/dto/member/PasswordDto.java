package com.message.messagequeue.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PasswordDto {

    private final String oldPassword;
    private final String newPassword;
}
