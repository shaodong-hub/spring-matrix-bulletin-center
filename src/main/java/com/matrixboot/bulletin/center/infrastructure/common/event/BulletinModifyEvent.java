package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinModifyEvent(Long id, Long createdBy, List<PictureEntity> pictures) {


    public boolean isNotEmpty() {
        return !CollectionUtils.isEmpty(pictures());
    }
}
