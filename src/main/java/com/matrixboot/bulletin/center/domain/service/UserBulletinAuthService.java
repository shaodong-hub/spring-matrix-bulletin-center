package com.matrixboot.bulletin.center.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * create in 2022/12/29 21:51
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SuppressWarnings("unused")
@Slf4j
@Service("check")
public class UserBulletinAuthService {

    public boolean hasBulletinAuthority(String userId, String bulletinId) {
        log.info("hasBulletinAuthority {} {}", userId, bulletinId);
        return true;
    }

}
