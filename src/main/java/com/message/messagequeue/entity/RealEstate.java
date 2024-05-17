package com.message.messagequeue.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "real_estate")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RealEstate extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "real_estate_id")
    private Long id;

    @Column(name = "real_estate_name")
    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "real_estate_city")),
            @AttributeOverride(name = "street", column = @Column(name = "real_estate_street")),
            @AttributeOverride(name = "detailAddress", column = @Column(name = "real_estate_detail_address")),
            @AttributeOverride(name = "zipcode", column = @Column(name = "real_estate_zipcode"))
    })
    private Address address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static RealEstate create(String name, Address Address, Member member) {
        return new RealEstate(name, Address, member);
    }

    private RealEstate(String name, Address address, Member member) {
        this.name = name;
        this.address = address;
        this.member = member;
    }
}
