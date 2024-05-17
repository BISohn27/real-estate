package com.message.messagequeue.init;

import com.message.messagequeue.entity.Member;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitMember {

    private final InitMemberService initMemberService;

    @PostConstruct
    public void init() {
        initMemberService.init();
    }

    public List<Member> getMembers() {
        return initMemberService.getMembers();
    }

    @Component
    static class InitMemberService {

        @PersistenceContext
        private EntityManager em;

        private final List<Member> members = new ArrayList<>();

        @Transactional
        public void init() {
            for (int i = 0; i < 100; i++) {
                Member member = new Member("member" + i);
                em.persist(member);
                members.add(member);
            }
        }

        protected List<Member> getMembers() {
            return members;
        }
    }
}
