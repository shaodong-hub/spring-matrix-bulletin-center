package com.matrixboot.bulletin.center.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

/**
 * create in 2022/11/29 23:58
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Setter
@Builder
@ToString
@TypeAlias("bulletin")
@Document(indexName = "bulletin")
public class BulletinElasticsearchEntity {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String text;

    @Field(type = FieldType.Date)
    private LocalDateTime createDate;

}
