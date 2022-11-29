package com.matrixboot.bulletin.center.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;


/**
 * create in 2022/11/28 23:51
 * CompoundIndex(name = "idx_region_age", def = "{'region':1,'age':1}")
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Data
@Document(collection = "bulletin")
public class BulletinMongoEntity {

    @Id
    private String id;

    @Indexed
    private Long userId;

    private String title;

    private String content;

    private Map<String, String> pics;

    private Long view;

    private Long favorite;

    private Integer status;

    @CreatedDate
    @Field("created_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Field("last_modified_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;

    /**
     * domainEvent
     *
     * @return Collection
     */
    @SuppressWarnings("unused")
    @DomainEvents
    public Collection<BulletinCreateEvent> domainEvent() {
        return Collections.singletonList(
                new BulletinCreateEvent(
                        this.getId(),
                        this.getUserId(),
                        this.pics
                )
        );
    }
}
