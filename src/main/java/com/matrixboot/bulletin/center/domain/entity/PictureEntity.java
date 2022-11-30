package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import com.matrixboot.bulletin.center.infrastructure.common.value.UserIdValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_IN_USED;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_UNUSED;

/**
 * create in 2022/11/30 00:20
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "picture")
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "user_id", columnDefinition = "BIGINT NOT NULL COMMENT '用户 id'"))
    private UserIdValue userId;

    @Column(columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT 'objectKey'")
    private String objectKey;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT '' COMMENT 'url'")
    private String url;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(columnDefinition = "TINYINT DEFAULT 0 COMMENT '状态'"))
    private PictureStatusValue status;

    @Column(name = "bulletin_id", insertable = false, updatable = false)
    private Long bulletinId;

    @JoinColumn(name = "bulletin_id", referencedColumnName = "id")
    @ManyToOne(targetEntity = BulletinEntity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JsonBackReference
    private BulletinEntity bulletin;

    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Contract(" -> new")
    public static @NotNull PictureEntity defaultPicture() {
        return new PictureEntity();
    }

    public PictureEntity userId(long userId) {
        this.userId = new UserIdValue(userId);
        return this;
    }

    public PictureEntity url(String url) {
        this.url = url;
        return this;
    }

    public PictureEntity defaultStatus() {
        this.status = PictureStatusValue.unaudited();
        return this;
    }

    public PictureEntity inUsed() {
        this.status = PictureStatusValue.audited();
        return this;
    }

}
