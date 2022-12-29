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
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.util.CollectionUtils;

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
@Document(collection = "bulletin")
public class BulletinInfoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3504012983951639554L;

    @Id
    private String id;

    private TitleValue title;

    private ContentValue content;

    @JsonManagedReference
    private Set<PictureEntity> pictures;

    private BulletinStatusValue status;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long lastModifiedBy;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    protected Boolean deleted;

    @Version
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
