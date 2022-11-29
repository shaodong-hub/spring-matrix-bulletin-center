package com.matrixboot.bulletin.center.infrastructure.mapper;

import com.matrixboot.bulletin.center.domain.entity.PictureEntity;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * create in 2022/11/30 01:07
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Mapper(componentModel = SPRING, nullValuePropertyMappingStrategy = IGNORE, nullValueCheckStrategy = ALWAYS, unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IPictureMapper {

    PictureResult from(PictureEntity entity);
}
