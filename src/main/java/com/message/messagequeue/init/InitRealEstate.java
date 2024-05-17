package com.message.messagequeue.init;

import com.message.messagequeue.entity.Address;
import com.message.messagequeue.entity.Member;
import com.message.messagequeue.entity.RealEstate;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Slf4j
@Profile("local")
@Component
@RequiredArgsConstructor
public class InitRealEstate {

    private final InitRealEstateService initRealEstateService;
    private final InitMember initMember;

    @PostConstruct
    public void init() {
        log.info("member = {}", initMember.getMembers());
        initRealEstateService.init(initMember.getMembers());
    }

    @Component
    static class InitRealEstateService {

        @PersistenceContext
        private EntityManager em;

        @Transactional
        public void init(List<Member> members) {
            List<String> cities = List.of("서울", "대구", "부산", "광주", "경기");

            for (int i = 0; i < members.size(); i++) {
                Random random = new Random();
                Address address = new Address(
                        cities.get((int)(Math.random() * 5)),
                        "아무거리" + i,
                        String.format("%05d", random.nextInt(100000)),
                        "상세주소" + i);

                RealEstate realEstate = RealEstate.create("realEstate" + i, address, members.get(i));

                em.persist(realEstate);
            }
        }
    }
}
