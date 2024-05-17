package com.message.messagequeue.service;

import com.message.messagequeue.dto.member.MemberDto;
import com.message.messagequeue.dto.member.MemberSignUpDto;
import com.message.messagequeue.dto.member.PasswordDto;
import com.message.messagequeue.entity.Member;
import com.message.messagequeue.exception.member.InvalidPasswordException;
import com.message.messagequeue.exception.member.MemberFoundException;
import com.message.messagequeue.exception.member.MemberNotFoundException;
import com.message.messagequeue.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void signUp( MemberSignUpDto requestDto) {
        Member foundMember = memberRepository.findByUsername(requestDto.getUsername());

        if (foundMember != null) {
            throw new MemberFoundException();
        }
        Member member = Member.createMember(requestDto.getUsername(), requestDto.getPassword());
        memberRepository.save(member);
    }

    public MemberDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return  new MemberDto(member.getId(), member.getUsername());
    }

    public void delete(Long id) {
        Member foundMember = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        foundMember.delete();
    }

    public void changePassword(Long id, PasswordDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);

        if (member.isIncorrectPassword(requestDto.getOldPassword())) {
            throw new InvalidPasswordException();
        }
        member.changePassword(requestDto.getNewPassword());
    }
}
