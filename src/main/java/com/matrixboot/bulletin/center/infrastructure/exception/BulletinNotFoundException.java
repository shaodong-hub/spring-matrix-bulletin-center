package com.matrixboot.bulletin.center.infrastructure.exception;

import lombok.Getter;

/**
 * create in 2022/11/29 23:25
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class BulletinNotFoundException extends BulletinException {

    @Getter
    private final String id;

    public BulletinNotFoundException(String id) {
        this.id = id;
    }
}
