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

    PictureEntity preserve(UserInfo userInfo, PictureCreateCommand command);

    void remove(PictureEntity entity);
}
