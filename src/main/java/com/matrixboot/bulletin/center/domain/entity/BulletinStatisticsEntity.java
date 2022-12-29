package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * create in 2022/12/16 21:24
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
@AggregateRoot
@Document(collection = "bulletin_statistics")
public class BulletinStatisticsEntity {

    @Id
    private String id;

    private Long bulletinId;

    private Long view;

    private Long like;

    private Long favorite;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public BulletinStatisticsEntity(Long bulletinId) {
        this.bulletinId = bulletinId;
        this.view = 0L;
        this.favorite = 0L;
    }
}
