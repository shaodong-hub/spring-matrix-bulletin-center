package com.matrixboot.bulletin.center.domain.service;

/**
 * create in 2022/11/29 23:47
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IEventBus {

    /**
     * publishEvent
     *
     * @param event event
     */
    void publishEvent(Object event);

}
