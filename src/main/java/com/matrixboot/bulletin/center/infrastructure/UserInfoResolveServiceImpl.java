package com.matrixboot.bulletin.center.infrastructure;

import com.matrixboot.bulletin.common.core.UserInfo;
import com.matrixboot.bulletin.common.utils.IUserInfoResolveService;
import org.springframework.stereotype.Component;

/**
 * create in 2022/11/30 21:36
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Component
public class UserInfoResolveServiceImpl implements IUserInfoResolveService {

    @Override
    public UserInfo getUserInfoFromJwt(String jwt) {
        return new UserInfo(1L);
    }
}
