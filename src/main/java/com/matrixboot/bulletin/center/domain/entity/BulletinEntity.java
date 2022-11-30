package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * create in 2022/11/28 23:51
 * CompoundIndex(name = "idx_region_age", def = "{'region':1,'age':1}")
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
@AggregateRoot
@Entity
@Table(name = "bulletin")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class BulletinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'")
    private Long userId;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '标题'")
    private String title;

    @Column(columnDefinition = "VARCHAR(255) DEFAULT '' COMMENT '内容'")
    private String content;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '查看次数'")
    private Long view;

    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '收藏次数'")
    private Long favorite;

    @Column(columnDefinition = "TINYINT DEFAULT 0 COMMENT '状态'")
    private Integer status;

    @OneToMany(mappedBy = "bulletin", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JsonManagedReference
    private List<PictureEntity> pictures;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    public BulletinEntity unaudited() {
        this.status = 0;
        return this;
    }

    /**
     * domainEvent
     *
     * @return Collection
     */
    @SuppressWarnings("unused")
    @DomainEvents
    public Collection<BulletinModifyEvent> domainEvent() {
        return Collections.singletonList(
                new BulletinModifyEvent(
                        this.getId(),
                        this.getUserId(),
                        this.pictures
                )
        );
    }
}
