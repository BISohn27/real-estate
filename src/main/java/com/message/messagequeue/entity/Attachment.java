package com.message.messagequeue.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Attachment {

    @Id
    @GeneratedValue
    @Column(name = "attachment_id")
    private Long id;

    @Column(name = "ori_file_name")
    private String originalFileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "is_deleted")
    private int isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private RealEstate realEstate;
}
