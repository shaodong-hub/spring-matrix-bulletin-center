package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

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
@EqualsAndHashCode(of = "id")
public class PictureEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330355043156162676L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "object_key", columnDefinition = "VARCHAR(50) DEFAULT '' COMMENT 'objectKey'")
    private String objectKey;

    @Column(columnDefinition = "VARCHAR(150) DEFAULT '' COMMENT 'url'")
    private String url;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(columnDefinition = "TINYINT DEFAULT 0 COMMENT '状态'"))
    private PictureStatusValue status;

    @Column(name = "bulletin_id", insertable = false, updatable = false)
    private Long bulletinId;

    @ManyToOne(targetEntity = BulletinInfoEntity.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JsonBackReference
    @ToString.Exclude
    private BulletinInfoEntity bulletin;

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

    @Contract(" -> new")
    public static @NotNull PictureEntity defaultPicture() {
        return new PictureEntity();
    }

    public void usedBy(BulletinInfoEntity entity) {
        this.bulletin = entity;
        this.status = PictureStatusValue.inUsed();
    }


    public PictureEntity url(String url) {
        this.url = url;
        return this;
    }

    public PictureEntity unaudited() {
        this.status = PictureStatusValue.inReview();
        return this;
    }

    public PictureEntity discarded() {
        this.status = PictureStatusValue.discarded();
        return this;
    }


    public PictureEntity inUsed() {
        this.status = PictureStatusValue.inUsed();
        return this;
    }
}
