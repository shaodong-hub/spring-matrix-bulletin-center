package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.domain.service.impl.PictureOssServiceImpl;
import com.matrixboot.bulletin.center.infrastructure.common.PictureConstant;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinDeleteEvent;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import com.matrixboot.bulletin.center.infrastructure.exception.PictureNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IPictureMapper;
import com.matrixboot.bulletin.common.core.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/30 00:32
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class PictureService {

    private final IPictureMapper mapper;

    private final PictureOssServiceImpl ossService;

    private final IPictureRepository repository;

    public PictureResult createPicture(UserInfo userInfo, PictureCreateCommand command) {
        var picture = ossService.preserve(userInfo, command);
        var entity = repository.save(picture);
        return mapper.from(entity);
    }

    public PictureResult deletePicture(@NotNull UserInfo userInfo, @NotNull PictureDeleteCommand command) {
        var optional = repository.findByIdAndUserIdAndStatus(command.id(), userInfo.userId(), PictureConstant.PICTURE_UNUSED);
        var entity = optional.orElseThrow(() -> new PictureNotFoundException(command.id()));
        repository.delete(entity);
        return mapper.from(entity);
    }

    /**
     * 发布成功以后更新图片状态
     *
     * @param event BulletinModifyEvent
     */
    public void bulletinCreate(@NotNull BulletinModifyEvent event) {
        event.pictures().forEach(k -> {
            var optional = repository.findById(k.getId());
            var entity = optional.orElseThrow(() -> new PictureNotFoundException(k.getId()));
            var entityInUsed = entity.inUsed();
            repository.save(entityInUsed);
        });
    }

    /**
     * 删除帖子同时删除图片
     *
     * @param event BulletinModifyEvent
     */
    public void bulletinDelete(@NotNull BulletinDeleteEvent event) {
        event.pictures().forEach(k -> {
            var optional = repository.findById(k.getId());
            var entity = optional.orElseThrow(() -> new PictureNotFoundException(k.getId()));
            ossService.remove(entity);
            repository.delete(entity);
        });
    }
}
