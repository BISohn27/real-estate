package com.message.messagequeue.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name="created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name="modified_date", updatable = false)
    private LocalDateTime modifiedDate;

    @PrePersist
    public void prePersist(){
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modifiedDate = LocalDateTime.now();
    }
}
