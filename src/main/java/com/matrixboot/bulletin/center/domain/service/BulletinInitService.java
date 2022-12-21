package com.matrixboot.bulletin.center.domain.service;

import com.matrixboot.bulletin.center.domain.entity.BulletinInfoEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

/**
 * create in 2022/12/21 14:23
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
public class BulletinInitService {

    public void initBulletin(@NotNull BulletinInfoEntity bulletin) {
        bulletin.unaudited();

    }
}
