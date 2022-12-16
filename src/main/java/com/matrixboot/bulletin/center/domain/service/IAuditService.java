package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.BulletinInfoEntity;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinAuditResult;

/**
 * create in 2022/11/30 19:17
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IAuditService {

    /**
     * 执行数据审计
     *
     * @param entity BulletinInfoEntity
     * @return BulletinAuditResult
     */
    BulletinAuditResult doAudit(BulletinInfoEntity entity);

}
