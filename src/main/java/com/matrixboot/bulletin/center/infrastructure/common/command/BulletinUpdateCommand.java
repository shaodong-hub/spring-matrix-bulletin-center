package com.matrixboot.bulletin.center.infrastructure.common.command;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * create in 2022/11/29 22:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinUpdateCommand(@NotNull Long id,
                                    String title,
                                    String content,
                                    Set<Long> pictureIds) {
}
