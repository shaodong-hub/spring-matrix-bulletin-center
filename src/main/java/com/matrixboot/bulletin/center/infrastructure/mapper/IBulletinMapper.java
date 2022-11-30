package com.matrixboot.bulletin.center.infrastructure.mapper;

import com.matrixboot.bulletin.center.domain.entity.BulletinElasticsearchEntity;
import com.matrixboot.bulletin.center.domain.entity.BulletinEntity;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * create in 2022/11/29 23:15
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Mapper(componentModel = SPRING,
        nullValuePropertyMappingStrategy = IGNORE,
        nullValueCheckStrategy = ALWAYS,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBulletinMapper {

    /**
     * BulletinEntity
     *
     * @param userInfo UserInfo
     * @param command  BulletinCreateCommand
     * @return BulletinEntity
     */
    BulletinEntity from(UserInfo userInfo, BulletinCreateCommand command);

    /**
     * BulletinResult
     *
     * @param entity BulletinEntity
     * @return BulletinResult
     */
    BulletinResult from(BulletinEntity entity);

    /**
     * update
     *
     * @param entity  BulletinEntity
     * @param command BulletinUpdateCommand
     */
    @InheritConfiguration
    void update(BulletinEntity entity, @MappingTarget BulletinUpdateCommand command);

    /**
     * BulletinElasticsearchEntity
     *
     * @param event BulletinModifyEvent
     * @return BulletinElasticsearchEntity
     */
    BulletinElasticsearchEntity from(BulletinModifyEvent event);

}
