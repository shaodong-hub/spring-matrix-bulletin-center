package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.BulletinInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * create in 2022/11/29 00:00
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IBulletinInfoRepository extends MongoRepository<BulletinInfoEntity, String> {

    /**
     * findAllByUserId
     *
     * @param createdBy createdBy
     * @param pageable  Pageable
     * @param <T>       T
     * @return Page
     */
    <T> Page<T> findAllByCreatedBy(String createdBy, Pageable pageable);


}
