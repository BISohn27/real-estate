package com.message.messagequeue.controller;

import com.message.messagequeue.dto.member.MemberDto;
import com.message.messagequeue.dto.member.MemberSignUpDto;
import com.message.messagequeue.dto.member.PasswordDto;
import com.message.messagequeue.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<String> signUp(@RequestBody MemberSignUpDto requestDto) {
        memberService.signUp(requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/members/{id}")
    public ResponseEntity<MemberDto> getMember(@PathVariable Long id) {
        MemberDto memberDto = memberService.getMember(id);
        return new ResponseEntity<>(memberDto, HttpStatus.OK);
    }

    @PutMapping("/api/members/{id]/password")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody PasswordDto requestDto) {
        memberService.changePassword(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/api/members/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        memberService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
