package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.common.core.UserInfo;

/**
 * create in 2022/11/30 19:27
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IPictureHoldService {

    /**
     * preserve
     *
     * @param userInfo UserInfo
     * @param command  PictureCreateCommand
     * @return PictureEntity
     */
    PictureEntity preserve(UserInfo userInfo, PictureCreateCommand command);

    /**
     * remove picture
     *
     * @param entity PictureEntity
     */
    void remove(PictureEntity entity);
}
