package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IBulletinRepository;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * create in 2022/11/28 23:59
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Service
@RequiredArgsConstructor
public class BulletinService {

    private final IBulletinRepository repository;

    public Page<BulletinResult> findByUserId(long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }

}
