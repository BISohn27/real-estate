package com.message.messagequeue.dto.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MemberDto {

    private final Long id;
    private final String username;
}
