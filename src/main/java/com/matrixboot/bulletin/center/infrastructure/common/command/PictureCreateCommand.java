package com.matrixboot.bulletin.center.infrastructure.common.command;

import org.springframework.web.multipart.MultipartFile;

/**
 * create in 2022/11/30 00:35
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record PictureCreateCommand(MultipartFile file) {
}
