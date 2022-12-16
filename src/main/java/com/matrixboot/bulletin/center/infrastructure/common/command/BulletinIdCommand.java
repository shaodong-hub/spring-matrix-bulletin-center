package com.matrixboot.bulletin.center.infrastructure.common.command;

import javax.validation.constraints.NotNull;

/**
 * create in 2022/12/16 22:59
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinIdCommand(@NotNull Long id) {
}
