package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
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
@Entity
@Table(name = "bulletin_statistics", indexes = {@Index(name = "UK_BULLETIN_ID", columnList = "bulletin_id", unique = true)})
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class BulletinStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bulletin_id", columnDefinition = "BIGINT DEFAULT 0 COMMENT 'bulletinId'")
    private Long bulletinId;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '查看次数'")
    private Long view;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '点赞次数'")
    private Long like;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '收藏次数'")
    private Long favorite;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", columnDefinition = "DATETIME  COMMENT '创建时间'")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private LocalDateTime lastModifiedDate;

    public BulletinStatisticsEntity(Long bulletinId) {
        this.bulletinId = bulletinId;
        this.view = 0L;
        this.favorite = 0L;
    }
}
