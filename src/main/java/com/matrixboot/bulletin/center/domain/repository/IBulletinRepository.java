package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.BulletinEntity;
import com.matrixboot.bulletin.center.infrastructure.common.value.UserIdValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create in 2022/11/29 00:00
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IBulletinRepository extends JpaRepository<BulletinEntity, Long> {

    /**
     * findAllByUserId
     *
     * @param userId   userId
     * @param pageable Pageable
     * @param <T>      T
     * @return Page
     */
    <T> Page<T> findAllByUserId(UserIdValue userId, Pageable pageable);
}
