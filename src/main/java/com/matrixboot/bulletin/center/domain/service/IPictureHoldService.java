package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;

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
     * @param command PictureCreateCommand
     * @return PictureEntity
     */
    PictureEntity preserve(PictureCreateCommand command);

    /**
     * remove picture
     *
     * @param entity PictureEntity
     */
    void remove(PictureEntity entity);
}
