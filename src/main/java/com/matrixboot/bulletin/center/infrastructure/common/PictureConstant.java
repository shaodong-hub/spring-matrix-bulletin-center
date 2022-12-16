package com.matrixboot.bulletin.center.infrastructure.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * create in 2022/11/30 00:55
 *
 * @author shishaodong
 * @version 0.0.1
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PictureConstant {

    public static final int PICTURE_IN_REVIEW = 0;

    public static final int PICTURE_ACCEPTED = 1;

    public static final int PICTURE_REJECTED = -1;

    public static final int PICTURE_DISCARDED = -2;

    public static final int PICTURE_IN_USED = 2;

}
