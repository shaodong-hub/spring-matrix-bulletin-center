package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.MatrixUserInfo;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.domain.service.impl.PictureOssServiceImpl;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinDeleteEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import com.matrixboot.bulletin.center.infrastructure.exception.PictureNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IPictureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.security.access.prepost.PreAuthorize;
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
@CacheConfig(cacheNames = "manage")
public class PictureService {

    private final IPictureMapper mapper;

    private final PictureOssServiceImpl ossService;

    private final IPictureRepository repository;

    @CachePut(key = "'picture:' + #user.id() + ':' + #result.id()", unless = "null == #result.id()")
    public PictureResult createPicture(MatrixUserInfo user, PictureCreateCommand command) {
        var picture = ossService.preserve(command);
        var entity = repository.save(picture);
        return mapper.from(entity);
    }

    @CacheEvict(key = "'picture:' + #user.id() + ':' + #result.id()")
    @PreAuthorize("@check.hasBulletinAuthority(#user.id(), #command.id())")
    public PictureResult deletePicture(@NotNull MatrixUserInfo user, @NotNull PictureDeleteCommand command) {
        var optional = repository.findByIdAndCreatedByAndStatus(command.id(), user.id(), PictureStatusValue.discarded());
        var entity = optional.orElseThrow(() -> new PictureNotFoundException(command.id()));
        repository.delete(entity);
        return mapper.from(entity);
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
