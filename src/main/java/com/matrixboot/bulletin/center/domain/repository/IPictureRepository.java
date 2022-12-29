package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

/**
 * create in 2022/11/30 00:26
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IPictureRepository extends MongoRepository<PictureEntity, String> {

    /**
     * deleteAllByCreatedDateBeforeAndStatusIs
     *
     * @param createdDate createdDate
     * @param status      status
     * @return count
     */
    long deleteAllByCreatedDateBeforeAndStatusIs(LocalDateTime createdDate, PictureStatusValue status);

    /**
     * findByIdAndUserIdAndStatus
     *
     * @param id        id
     * @param createdBy userId
     * @param status    status
     * @return Optional
     */
    Optional<PictureEntity> findByIdAndCreatedByAndStatus(String id, String createdBy, PictureStatusValue status);

    /**
     * findAllByIdIn
     *
     * @param ids ids
     * @return Set
     */
    Set<PictureEntity> findAllByIdIn(Set<String> ids);

}
