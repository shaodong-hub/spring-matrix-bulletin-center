package com.matrixboot.bulletin.center.infrastructure.common.command;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.Map;

/**
 * create in 2022/11/29 22:56
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinCreateCommand(@Length(min = 1, max = 20) String title,
                                    @Length(min = 1, max = 20) String content,
                                    @Size(min = 1, max = 6) Map<String, String> pictures) {
}
