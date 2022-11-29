package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

/**
 * create in 2022/11/30 00:08
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Controller
public class BulletinEventFacade {


    @EventListener(BulletinCreateEvent.class)
    public void bulletinCreate(BulletinCreateEvent event) {

    }

}
