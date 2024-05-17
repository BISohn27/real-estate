package com.message.messagequeue.repository;

import com.message.messagequeue.entity.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealEstateRepository extends JpaRepository <RealEstate, Long> {
}
