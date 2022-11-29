package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.domain.service.PictureOssService;
import com.matrixboot.bulletin.center.infrastructure.common.PictureConstant;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import com.matrixboot.bulletin.center.infrastructure.exception.PictureNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IPictureMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    private final PictureOssService ossService;

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

    public void bulletinCreate(@NotNull BulletinCreateEvent event) {
        event.pics().forEach((k, v) -> {
            var optional = repository.findById(k);
            var entity = optional.orElseThrow(() -> new PictureNotFoundException(k));
            var entityInUsed = entity.inUsed();
            repository.save(entityInUsed);
        });
    }
}
