package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * create in 2022/11/30 00:26
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IPictureRepository extends PagingAndSortingRepository<PictureEntity, String> {

    /**
     * deleteAllByCreatedDateBeforeAndStatusIs
     *
     * @param createdDate createdDate
     * @param status      status
     * @return count
     */
    long deleteAllByCreatedDateBeforeAndStatusIs(LocalDateTime createdDate, int status);

    int deleteByUserIdAndStatus(int userId, int status);

    Optional<PictureEntity> findByUserIdAndStatus(long userId, int status);

    Optional<PictureEntity> findByIdAndUserIdAndStatus(String id, long userId, int status);

}
