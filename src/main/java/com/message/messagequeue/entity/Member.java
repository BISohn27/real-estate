package com.message.messagequeue.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(uniqueConstraints = { @UniqueConstraint(name = "member_unique_idx1", columnNames = { "username" }) })
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private String password;

    @Column(name = "is_deleted")
    private int isDeleted;

    public Member(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static Member createMember(String username, String password) {
        Member member = new Member();
        member.username = username;
        member.password = member.getHashedPassword(password);

        return member;
    }

    private String getHashedPassword(String plainPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(plainPassword.getBytes(StandardCharsets.UTF_8));
            return new String(Base64.getEncoder().encode(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            log.error("Error Msg = {}", e.getMessage(), e);
            throw new IllegalStateException(e);
        }
    }

    public boolean isIncorrectPassword(String plainPassword) {
        return password.equals(getHashedPassword(plainPassword));
    }

    public void changePassword(String plainPassword) {
        String hashedPassword = getHashedPassword(plainPassword);
    }

    public void delete() {
        isDeleted = 1;
    }
}
