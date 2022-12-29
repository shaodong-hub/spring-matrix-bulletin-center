package com.matrixboot.bulletin.center.infrastructure.mapper;

import com.matrixboot.bulletin.center.domain.entity.BulletinInfoEntity;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.center.infrastructure.common.value.StatisticsValue;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
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
@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBulletinMapper {

    /**
     * BulletinResult
     *
     * @param entity BulletinInfoEntity
     * @return BulletinResult
     */
    @Mapping(target = "status", source = "entity.status.status")
    @Mapping(target = "title", source = "entity.title.title")
    @Mapping(target = "content", source = "entity.content.content")
    BulletinResult from(BulletinInfoEntity entity);

    /**
     * BulletinInfoEntity
     *
     * @param command BulletinCreateCommand
     * @return BulletinInfoEntity
     */

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "replaceTitle", ignore = true)
    @Mapping(target = "replaceContent", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "pictures", ignore = true)
    @Mapping(target = "title", expression = "java(new TitleValue(command.title()))")
    @Mapping(target = "content", expression = "java(new ContentValue(command.content()))")
    BulletinInfoEntity from(BulletinCreateCommand command);

    /**
     * StatisticsValue
     *
     * @return statistics
     */
    @Named("InitStatistics")
    default StatisticsValue statistics() {
        return StatisticsValue.defaultStatisticsValue();
    }

    /**
     * update
     *
     * @param entity  BulletinInfoEntity
     * @param command BulletinUpdateCommand
     */
    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "replaceTitle", ignore = true)
    @Mapping(target = "replaceContent", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "title", expression = "java(new TitleValue(command.title()))")
    @Mapping(target = "content", expression = "java(new ContentValue(command.content()))")
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    void update(@MappingTarget BulletinInfoEntity entity, BulletinUpdateCommand command);
}
