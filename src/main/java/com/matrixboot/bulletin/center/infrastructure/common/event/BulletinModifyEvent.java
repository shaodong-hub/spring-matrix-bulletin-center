package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import org.springframework.util.CollectionUtils;

import java.util.Set;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinModifyEvent(String id, Long createdBy, Set<PictureEntity> pictures) implements IBulletinEvent {


    public boolean isNotEmpty() {
        return !CollectionUtils.isEmpty(pictures());
    }
}
