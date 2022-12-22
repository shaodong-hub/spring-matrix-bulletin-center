package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IBulletinInfoRepository;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.domain.service.BulletinInitService;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.center.infrastructure.exception.BulletinNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IBulletinMapper;
import com.matrixboot.bulletin.common.core.UserInfo;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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

    @Cacheable(key = "'bulletins-user:' + #userInfo.userId()")
    public Page<BulletinResult> findCurrentBulletins(@NotNull UserInfo userInfo, Pageable pageable) {
        log.info("findCurrentBulletins {}", userInfo.userId());
        return repository.findAllByCreatedBy(userInfo.userId(), pageable);
    }

    @Caching(
            put = {
                    @CachePut(key = "'id-bulletin:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'id-user:' + #result.userId()", unless = "null == #result.userId()")
            },
            evict = {@CacheEvict(key = "'bulletins-user:' + #result.id()")}
    )
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult create(@Valid BulletinCreateCommand command) {
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
                    @CachePut(key = "'id-bulletin:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'id-user:' + #result.userId()", unless = "null == #result.userId()")
            },
            evict = {@CacheEvict(key = "'bulletins-user:' + #result.id()")}
    )
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult update(@NotNull @Valid BulletinUpdateCommand command) {
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
            @CacheEvict(key = "'id:bulletins:' + #result.id()"),
            @CacheEvict(key = "'id-bulletin:' + #result.id()"),
            @CacheEvict(key = "'id-user:' + #result.userId()")
    })
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult delete(@NotNull @Valid BulletinDeleteCommand command) {
        var optional = repository.findById(command.id());
        var persistBulletin = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        persistBulletin.delete();
        var result = mapper.from(persistBulletin);
        log.info(String.valueOf(result));
        return result;
    }
}
