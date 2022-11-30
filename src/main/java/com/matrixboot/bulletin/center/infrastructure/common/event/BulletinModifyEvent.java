package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.value.UserIdValue;

import java.util.List;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinModifyEvent(Long id, UserIdValue userId, List<PictureEntity> pictures) {
}
