package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IBulletinRepository;
import com.matrixboot.bulletin.center.domain.service.IAuditService;
import com.matrixboot.bulletin.center.domain.service.IEventBus;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.exception.BulletinNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/30 19:09
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BulletinAuditService {

    private final IAuditService auditService;

    private final IBulletinRepository bulletinRepository;

    private final IEventBus eventBus;

    public void audit(@NotNull BulletinModifyEvent event) {
        var entity = bulletinRepository.findById(event.id()).orElseThrow(() -> new BulletinNotFoundException(event.id()));
        var result = auditService.doAudit(entity);
        var bulletin = bulletinRepository.save(entity);
        log.info("{}", bulletin);
        eventBus.publishEvent(result);
    }
}
