package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.BulletinMongoEntity;
import com.matrixboot.bulletin.center.domain.repository.IBulletinRepository;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.center.infrastructure.exception.BulletinNotFoundException;
import com.matrixboot.bulletin.center.infrastructure.mapper.IBulletinMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

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
@CacheConfig(cacheNames = "bulletin")
public class BulletinService {

    private final IBulletinMapper mapper;

    private final IBulletinRepository repository;


    public Page<BulletinResult> findCurrentBulletins(@NotNull UserInfo userInfo, Pageable pageable) {
        return repository.findAllByUserId(userInfo.userId(), pageable);
    }

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'user-id:' + #result.userId()", unless = "null == #result.userId()")
    })
    public BulletinResult create(UserInfo userInfo, @Valid BulletinCreateCommand command) {
        BulletinMongoEntity entity = mapper.from(userInfo, command);
        BulletinMongoEntity save = repository.save(entity);
        var result = mapper.from(save);
        log.info(String.valueOf(result));
        return result;
    }

    @Caching(put = {
            @CachePut(key = "'id:' + #result.id()", unless = "null == #result.id()"),
            @CachePut(key = "'user-id:' + #result.userId()", unless = "null == #result.userId()")
    })
    public BulletinResult update(UserInfo userInfo, @NotNull @Valid BulletinUpdateCommand command) {
        Optional<BulletinMongoEntity> optional = repository.findById(command.id());
        BulletinMongoEntity entity = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        mapper.update(entity, command);
        entity = repository.save(entity);
        var result = mapper.from(entity);
        log.info(String.valueOf(result));
        return result;
    }

    @Caching(evict = {
            @CacheEvict(key = "'id:' + #result.id()"),
            @CacheEvict(key = "'user-id:' + #result.userId()")
    })
    public BulletinResult delete(UserInfo userInfo, @NotNull @Valid BulletinDeleteCommand command) {
        Optional<BulletinMongoEntity> optional = repository.findById(command.id());
        BulletinMongoEntity entity = optional.orElseThrow(() -> new BulletinNotFoundException(command.id()));
        repository.delete(entity);
        var result = mapper.from(entity);
        log.info(String.valueOf(result));
        return result;
    }
}
