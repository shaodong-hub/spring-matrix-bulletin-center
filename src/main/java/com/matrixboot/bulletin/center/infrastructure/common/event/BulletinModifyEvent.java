package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;

import java.util.List;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinModifyEvent(Long id, Long userId, List<PictureEntity> pictures) {
}
