package com.matrixboot.bulletin.center.domain.repository;

import com.matrixboot.bulletin.center.domain.entity.BulletinStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;

/**
 * create in 2022/12/16 21:32
 *
 * @author shishaodong
 * @version 0.0.1
 */
public interface IBulletinStatisticsRepository extends JpaRepository<BulletinStatisticsEntity, Long> {

    /**
     * existsByBulletinId
     *
     * @param bulletinId bulletinId
     * @return boolean
     */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    boolean existsByBulletinId(long bulletinId);

    /**
     * increaseView
     *
     * @param id id
     */
    @Modifying
    @Query("UPDATE BulletinStatisticsEntity AS bulletin SET bulletin.view = (1 + bulletin.view) WHERE bulletin.id = :id")
    void increaseView(@Param("id") long id);

    /**
     * increaseFavorite
     *
     * @param id id
     */
    @Modifying
    @Query("UPDATE BulletinStatisticsEntity AS bulletin SET bulletin.favorite = (1 + bulletin.favorite) WHERE bulletin.id = :id")
    void increaseFavorite(@Param("id") long id);

}