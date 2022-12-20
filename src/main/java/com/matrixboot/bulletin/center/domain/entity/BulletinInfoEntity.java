package com.matrixboot.bulletin.center.domain.entity;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.value.BulletinStatusValue;
import com.matrixboot.bulletin.center.infrastructure.common.value.ContentValue;
import com.matrixboot.bulletin.center.infrastructure.common.value.TitleValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;


/**
 * create in 2022/11/28 23:51
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
public class BulletinInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3504012983951639554L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "title", column = @Column(columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT '标题'"))
    private TitleValue title;

    @Embedded
    @AttributeOverride(name = "content", column = @Column(columnDefinition = "VARCHAR(255) DEFAULT '' COMMENT '内容'"))
    private ContentValue content;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(columnDefinition = "TINYINT DEFAULT 0 COMMENT '状态'"))
    private BulletinStatusValue status;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bulletin_id")
    @JsonManagedReference
    private Set<PictureEntity> pictures;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'")
    private Long createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'")
    private Long lastModifiedBy;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", columnDefinition = "DATETIME  COMMENT '创建时间'")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private LocalDateTime lastModifiedDate;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '乐观锁'")
    private Long version;

    public void replacePictures(@NotNull Set<PictureEntity> pictures) {
        if (CollUtil.isNotEmpty(this.pictures)) {
            pictures.forEach(PictureEntity::discarded);
        }
        AtomicReference<BulletinInfoEntity> reference = new AtomicReference<>(this);
        this.pictures = pictures;
        pictures.forEach(picture -> picture.usedBy(reference.get()));
    }

    public BulletinInfoEntity unaudited() {
        this.status = BulletinStatusValue.unaudited();
        return this;
    }

    public BulletinInfoEntity audited() {
        this.status = BulletinStatusValue.audited();
        return this;
    }

    public BulletinInfoEntity initBulletin() {
        return this;
    }

    public BulletinInfoEntity replaceTitle(String title) {
        this.title = new TitleValue(title);
        return this;
    }

    public BulletinInfoEntity replaceContent(String content) {
        this.content = new ContentValue(content);
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
                        this.getCreatedBy(),
                        this.pictures
                )
        );
    }
}
