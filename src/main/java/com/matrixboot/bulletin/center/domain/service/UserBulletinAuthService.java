package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.application.service.BulletinPictureService;
import com.matrixboot.bulletin.center.application.service.BulletinUserService;
import jakarta.annotation.Resource;
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

    @Resource
    private BulletinUserService bulletinUserService;

    @Resource
    private BulletinPictureService bulletinPictureService;


    public boolean hasBulletinAuthority(String userId, String bulletinId) {
        boolean exists = bulletinUserService.existsByUserIdAndBulletinId(userId, bulletinId);
        log.info("hasBulletinAuthority {} {} {}", exists, userId, bulletinId);
        return exists;
    }

    public boolean hasPictureAuthority(String userId, String pictureId) {
        boolean exists = bulletinPictureService.existsByUserIdAndPictureId(userId, pictureId);
        log.info("hasPictureAuthority {} {} {}", exists, userId, pictureId);
        return exists;
    }
}
