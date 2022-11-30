package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinAuditService;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

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

    @EventListener(BulletinModifyEvent.class)
    public void bulletinModify(BulletinModifyEvent event) {
        auditService.audit(event);
    }

}
