package com.matrixboot.bulletin.center.infrastructure.exception;

import lombok.Getter;

/**
 * create in 2022/11/29 23:25
 *
 * @author shishaodong
 * @version 0.0.1
 */
public class PictureNotFoundException extends BulletinException {

    @Getter
    private final Long id;

    public PictureNotFoundException(Long id) {
        this.id = id;
    }
}
