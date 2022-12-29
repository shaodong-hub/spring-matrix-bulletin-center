package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.MatrixUserInfo;
import com.matrixboot.bulletin.center.domain.repository.IBulletinInfoRepository;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.domain.service.BulletinInitService;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.center.infrastructure.exception.BulletinNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IBulletinMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


/**
 * create in 2022/11/28 23:59
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Validated
@CacheConfig(cacheNames = "manage")
public class BulletinUserService {

    private final IBulletinMapper mapper;

    private final BulletinInitService initService;

    private final IBulletinInfoRepository repository;

    private final IPictureRepository pictureRepository;

    @Cacheable(key = "'user-bulletins:' + #user.id()")
    public Page<BulletinResult> findCurrentBulletins(@NotNull MatrixUserInfo user, Pageable pageable) {
        log.info("findCurrentBulletins {}", user.id());
        return repository.findAllByCreatedBy(user.id(), pageable);
    }

    @Caching(
            put = {
                    @CachePut(key = "'bulletin:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'user:' + #result.userId()", unless = "null == #result.userId()")
            },
            evict = {@CacheEvict(key = "'user-bulletins:' + #result.userId()")}
    )
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult create(MatrixUserInfo user, @Valid BulletinCreateCommand command) {
        var transientBulletin = mapper.from(command);
        var results = pictureRepository.findAllByIdIn(command.pictureIds());
        log.info("填充图片信息 {} {}", command, results.size());
        transientBulletin.updatePictures(results);
        initService.initBulletin(transientBulletin);
        var persistBulletin = repository.save(transientBulletin);
        var result = mapper.from(persistBulletin);
        log.info(String.valueOf(result));
        return result;
    }

    @Caching(
            put = {
                    @CachePut(key = "'bulletin:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'user:' + #result.userId()", unless = "null == #result.id()")
            },
            evict = {@CacheEvict(key = "'user-bulletins:' + #result.userId()")}
    )
    @PreAuthorize("@check.hasBulletinAuthority(#user.id(), #command.id())")
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult update(MatrixUserInfo user, @NotNull @Valid BulletinUpdateCommand command) {
        var optional = repository.findById(command.id());
        var persistBulletin = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        mapper.update(persistBulletin, command);
        var results = pictureRepository.findAllByIdIn(command.pictureIds());
        persistBulletin.updatePictures(results);
        var result = mapper.from(persistBulletin);
        log.info(String.valueOf(persistBulletin));
        return result;
    }

    @Caching(evict = {
            @CacheEvict(key = "'bulletin:' + #result.id()"),
            @CacheEvict(key = "'user:' + #result.userId()"),
            @CacheEvict(key = "'user-bulletins:' + #result.userId()")
    })
    @PreAuthorize("@check.hasBulletinAuthority(#user.id(), #command.id())")
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult delete(MatrixUserInfo user, @NotNull @Valid BulletinDeleteCommand command) {
        var optional = repository.findById(command.id());
        var persistBulletin = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        persistBulletin.delete();
        var result = mapper.from(persistBulletin);
        log.info(String.valueOf(result));
        return result;
    }
}
