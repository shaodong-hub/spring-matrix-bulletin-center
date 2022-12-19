package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.domain.repository.IBulletinInfoRepository;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
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
import java.util.Set;

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
                    @CachePut(key = "'id-user:'     + #result.userId()", unless = "null == #result.userId()")
            },
            evict = {@CacheEvict(key = "'bulletins-user:' + #result.id()")}
    )
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult create(@Valid BulletinCreateCommand command) {
        var bulletin = mapper.from(command);
        var unSavedEntity = bulletin.initBulletin();
        Set<PictureEntity> results = pictureRepository.findAllByIdIn(command.pictureIds());
        unSavedEntity.addPictures(results);
        var savedEntity = repository.save(unSavedEntity);
        var result = mapper.from(savedEntity);
        log.info(String.valueOf(result));
        return result;
    }

    @Caching(
            put = {
                    @CachePut(key = "'id-bulletin:' + #result.id()", unless = "null == #result.id()"),
                    @CachePut(key = "'id-user:'     + #result.userId()", unless = "null == #result.userId()")
            },
            evict = {@CacheEvict(key = "'bulletins-user:' + #result.id()")}
    )
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult update(@NotNull @Valid BulletinUpdateCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        mapper.update(entity, command);
        var savedEntity = repository.save(entity.unaudited());
        var result = mapper.from(savedEntity);
        log.info(String.valueOf(savedEntity));
        return result;
    }

    @Caching(evict = {
            @CacheEvict(key = "'id-bulletin:'  + #result.id()"),
            @CacheEvict(key = "'id-user:'      + #result.userId()"),
            @CacheEvict(key = "'id:bulletins:' + #result.id()"),
    })
    @Transactional(rollbackFor = Exception.class)
    public BulletinResult delete(@NotNull @Valid BulletinDeleteCommand command) {
        var optional = repository.findById(command.id());
        var entity = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        repository.delete(entity);
        var result = mapper.from(entity);
        log.info(String.valueOf(result));
        return result;
    }
}
