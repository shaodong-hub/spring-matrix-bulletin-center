package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/30 01:09
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
public class PictureOssService {

    public PictureEntity preserve(@NotNull UserInfo userInfo, PictureCreateCommand command) {
        return PictureEntity.defaultPicture().userId(userInfo.userId());
    }
}
