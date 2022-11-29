package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.BulletinMongoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * create in 2022/11/29 00:00
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IBulletinRepository extends PagingAndSortingRepository<BulletinMongoEntity, String> {

    /**
     * findAllByUserId
     *
     * @param userId   userId
     * @param pageable Pageable
     * @param <T>      T
     * @return Page
     */
    <T> Page<T> findAllByUserId(long userId, Pageable pageable);
}
