package com.matrixboot.bulletin.center.infrastructure.common.event;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;

import java.util.List;

/**
 * create in 2022/12/16 21:39
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinCreateEvent(Long bulletinId, Long createdBy,
                                  List<PictureEntity> pictures) implements IBulletinEvent {
}
