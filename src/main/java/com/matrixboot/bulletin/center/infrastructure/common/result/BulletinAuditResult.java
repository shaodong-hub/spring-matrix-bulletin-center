package com.matrixboot.bulletin.center.infrastructure.common.result;


import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * create in 2022/11/30 14:14
 *
 * @author shishaodong
 * @version 0.0.1
 */

public record BulletinAuditResult(Integer status, String message) implements Serializable {

    public static final int STATUS_SUCCESS = 0;

    public static final int STATUS_FAILURE = 1;

    public static final String MESSAGE_SUCCESS = "SUCCESS";
    public static final String MESSAGE_FAILURE = "FAILURE";

    @Contract(" -> new")
    public static @NotNull BulletinAuditResult success() {
        return new BulletinAuditResult(STATUS_SUCCESS, MESSAGE_SUCCESS);
    }

    @Contract(" -> new")
    public static @NotNull BulletinAuditResult failure() {
        return new BulletinAuditResult(STATUS_FAILURE, MESSAGE_FAILURE);
    }

}
