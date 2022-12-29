package com.matrixboot.bulletin.center.infrastructure.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.matrixboot.bulletin.common.core.UserInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.jackson.JsonComponent;


/**
 * create in 2022/11/29 23:53
 *
 * @author shishaodong
 * @version 0.0.1
 */
@JsonComponent
public class UserInfoDeserializer extends JsonDeserializer<UserInfo> {

    @Resource
    private HttpServletRequest request;

    @Override
    public UserInfo deserialize(JsonParser p, DeserializationContext ctxt) {
        return null;
    }
}
