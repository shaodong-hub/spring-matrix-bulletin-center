package com.matrixboot.bulletin.center.infrastructure.common.event;

import java.util.Map;

/**
 * create in 2022/11/29 23:31
 *
 * @author shishaodong
 * @version 0.0.1
 */
public record BulletinCreateEvent(String id, Long userId, Map<String, String> pics) {
}
