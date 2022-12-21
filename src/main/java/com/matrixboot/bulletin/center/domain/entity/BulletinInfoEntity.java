package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinDeleteEvent;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.event.IBulletinEvent;
import com.matrixboot.bulletin.center.infrastructure.common.value.BulletinStatusValue;
import com.matrixboot.bulletin.center.infrastructure.common.value.ContentValue;
import com.matrixboot.bulletin.center.infrastructure.common.value.TitleValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.CollectionUtils;

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
@Where(clause = "deleted = false OR deleted = null")
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "bulletin_id")
    @JsonManagedReference
    private Set<PictureEntity> pictures;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(columnDefinition = "TINYINT DEFAULT 0 COMMENT '状态'"))
    private BulletinStatusValue status;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'")
    private Long createdBy;

    @LastModifiedBy
    @Column(name = "last_modified_by", columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'")
    private Long lastModifiedBy;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", columnDefinition = "DATETIME COMMENT '创建时间'")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "last_modified_date", columnDefinition = "DATETIME COMMENT '最后更新时间'")
    private LocalDateTime lastModifiedDate;

    @Column(columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '该记录是否逻辑删除'")
    protected Boolean deleted;

    @Version
    @Column(columnDefinition = "BIGINT DEFAULT 0 COMMENT '乐观锁'")
    private Long version;

    /**
     * 更新图片
     *
     * @param pictures 图片
     */
    public void updatePictures(@NotNull Set<PictureEntity> pictures) {
        pictures.forEach(PictureEntity::inUsed);
        if (CollectionUtils.isEmpty(this.pictures)) {
            this.pictures = pictures;
            return;
        }
        if (!CollectionUtils.isEmpty(pictures)) {
            this.pictures.clear();
            this.pictures.addAll(pictures);
            this.status = BulletinStatusValue.unaudited();
        }
    }

    public void delete() {
        this.deleted = true;
    }

    public boolean isAudited() {
        var contentAudited = this.status.getStatus().equals(BulletinStatusValue.accepted().getStatus());
        var picturesAudited = this.getPictures().stream().allMatch(entity -> entity.getStatus().getStatus().equals(BulletinStatusValue.accepted().getStatus()));
        return contentAudited && picturesAudited;
    }


    public BulletinInfoEntity unaudited() {
        this.status = BulletinStatusValue.unaudited();
        return this;
    }

    public BulletinInfoEntity audited() {
        this.status = BulletinStatusValue.accepted();
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
    public Collection<IBulletinEvent> domainEvent() {
        if (Boolean.TRUE.equals(this.deleted)) {
            return Collections.singletonList(new BulletinDeleteEvent(this.getId(), this.getCreatedBy(), this.pictures));
        } else {
            return Collections.singletonList(new BulletinModifyEvent(this.getId(), this.getCreatedBy(), this.pictures));
        }
    }
}
