//package com.matrixboot.bulletin.center.infrastructure.converter;
//
//import com.matrixboot.bulletin.center.infrastructure.common.value.UserIdValue;
//import com.matrixboot.bulletin.common.utils.UserInfoContextHolder;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//import java.util.Objects;
//
///**
// * create in 2022/11/30 20:37
// *
// * @author shishaodong
// * @version 0.0.1
// */
//@Converter(autoApply = true)
//public class CurrentUserConverter implements AttributeConverter<UserIdValue, UserIdValue> {
//
//    @Override
//    public UserIdValue convertToDatabaseColumn(UserIdValue attribute) {
//        Long aLong = Objects.requireNonNull(UserInfoContextHolder.findCurrentUser()).userId();
//        return new UserIdValue(aLong);
//    }
//
//    @Override
//    public UserIdValue convertToEntityAttribute(UserIdValue dbData) {
//        return dbData;
//    }
//}
