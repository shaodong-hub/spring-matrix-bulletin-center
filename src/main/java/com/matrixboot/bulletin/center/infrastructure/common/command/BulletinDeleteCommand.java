package com.matrixboot.bulletin.center.infrastructure.common.command;


import jakarta.validation.constraints.NotNull;

/**
 * create in 2022/11/29 22:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinDeleteCommand(@NotNull String id) {
}
