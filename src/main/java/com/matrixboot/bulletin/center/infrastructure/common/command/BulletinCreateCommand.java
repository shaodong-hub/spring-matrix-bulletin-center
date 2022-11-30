package com.matrixboot.bulletin.center.infrastructure.common.command;

import org.hibernate.validator.constraints.Length;


/**
 * create in 2022/11/29 22:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinCreateCommand(@Length(min = 1, max = 20) String title,
                                    @Length(min = 1, max = 20) String content) {
}