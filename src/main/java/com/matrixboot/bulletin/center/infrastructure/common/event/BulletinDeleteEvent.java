package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;

import java.util.Set;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinDeleteEvent(String id, String createdBy, Set<PictureEntity> pictures) implements IBulletinEvent {
}
