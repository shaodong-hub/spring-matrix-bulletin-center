package com.matrixboot.bulletin.center.infrastructure.common.result;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * create in 2022/11/29 00:01
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinResult(Long id,
                             String userId,
                             String title,
                             String content,
                             Integer status,
                             LocalDateTime createdDate,
                             LocalDateTime lastModifiedDate) implements Serializable {
}
