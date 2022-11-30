package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.BulletinEntity;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinAuditResult;

/**
 * create in 2022/11/30 19:17
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IAuditService {

    BulletinAuditResult doAudit(BulletinEntity entity);

}
