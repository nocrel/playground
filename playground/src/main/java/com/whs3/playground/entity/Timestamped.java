package com.whs3.playground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class Timestamped {

    @CreatedDate
    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "MODIFIEDAT")
    private LocalDateTime modifiedAt;


}
