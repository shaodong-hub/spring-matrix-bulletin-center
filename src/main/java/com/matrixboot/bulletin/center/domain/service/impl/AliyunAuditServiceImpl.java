package com.matrixboot.bulletin.center.domain.service.impl;

import com.matrixboot.bulletin.center.domain.entity.BulletinInfoEntity;
import com.matrixboot.bulletin.center.domain.service.IAuditService;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinAuditResult;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/30 19:11
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
public class AliyunAuditServiceImpl implements IAuditService {

    @Override
    public BulletinAuditResult doAudit(BulletinInfoEntity entity) {
        var audited = entity.audited();
        return BulletinAuditResult.success();
    }

}
