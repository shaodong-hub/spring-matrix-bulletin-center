package com.matrixboot.bulletin.center.infrastructure.common.value;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * create in 2022/12/16 20:28
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class AuditValue implements Serializable {

    @Serial
    private static final long serialVersionUID = -3205223258200976658L;

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

}
