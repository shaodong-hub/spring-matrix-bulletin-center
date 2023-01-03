package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.annotation.AggregateRoot;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

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
@AggregateRoot
@EqualsAndHashCode
@Document(collection = "picture")
@TypeAlias("picture")
public class PictureEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 2330355043156162676L;

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String objectKey;

    private String url;


    private PictureStatusValue status;


    private String bulletinId;

    @JsonBackReference
    private BulletinInfoEntity bulletin;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String lastModifiedBy;

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
