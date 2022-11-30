package com.matrixboot.bulletin.center.infrastructure.bus;

import com.matrixboot.bulletin.center.domain.service.IEventBus;
import org.springframework.stereotype.Component;

/**
 * create in 2022/11/29 23:47
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class RabbitMqEventBus implements IEventBus {

    @Override
    public void publishEvent(Object event) {

    }
}
