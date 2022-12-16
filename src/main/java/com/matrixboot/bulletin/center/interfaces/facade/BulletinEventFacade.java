package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinAuditService;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.event.TransactionalEventListener;

import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

/**
 * create in 2022/11/30 00:08
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Controller
@RequiredArgsConstructor
public class BulletinEventFacade {

    private final BulletinAuditService auditService;

    @TransactionalEventListener(value = BulletinModifyEvent.class, condition = "#event.isNotEmpty()", phase = BEFORE_COMMIT)
    public void bulletinModify(BulletinModifyEvent event) {
        auditService.audit(event);
    }

}
