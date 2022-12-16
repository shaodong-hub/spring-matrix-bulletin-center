package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * create in 2022/11/30 00:26
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IPictureRepository extends JpaRepository<PictureEntity, Long> {

    /**
     * deleteAllByCreatedDateBeforeAndStatusIs
     *
     * @param createdDate createdDate
     * @param status      status
     * @return count
     */
    long deleteAllByCreatedDateBeforeAndStatusIs(LocalDateTime createdDate, int status);

    /**
     * findByIdAndUserIdAndStatus
     *
     * @param id     id
     * @param userId userId
     * @param status status
     * @return Optional
     */
    Optional<PictureEntity> findByIdAndCreatedByAndStatus(long id, long userId, int status);


}
