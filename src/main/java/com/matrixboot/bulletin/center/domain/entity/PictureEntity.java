package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_IN_USED;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_UNUSED;

/**
 * create in 2022/11/30 00:20
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Data
@Document(collection = "picture")
public class PictureEntity {

    @Id
    private String id;

    @Indexed
    private Long userId;

    private String url;

    private Integer status;

    @CreatedDate
    @Field("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    @Contract(" -> new")
    public static @NotNull PictureEntity defaultPicture() {
        return new PictureEntity();
    }

    public PictureEntity userId(long userId) {
        this.setUserId(userId);
        return this;
    }

    public PictureEntity url(String url) {
        this.setUrl(url);
        return this;
    }

    public PictureEntity defaultStatus() {
        this.setStatus(PICTURE_UNUSED);
        return this;
    }

    public PictureEntity inUsed(){
        this.setStatus(PICTURE_IN_USED);
        return this;
    }

}
