package com.matrixboot.bulletin.center.infrastructure.common.value;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_ACCEPTED;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_DISCARDED;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_IN_REVIEW;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_IN_USED;
import static com.matrixboot.bulletin.center.infrastructure.common.PictureConstant.PICTURE_REJECTED;

/**
 * create in 2022/11/30 23:54
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PictureStatusValue implements Serializable {

    @Serial
    private static final long serialVersionUID = 1970945863807987974L;

    private Integer status;

    @Contract(" -> new")
    public static @NotNull PictureStatusValue inReview() {
        return new PictureStatusValue(PICTURE_IN_REVIEW);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue accepted() {
        return new PictureStatusValue(PICTURE_ACCEPTED);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue rejected() {
        return new PictureStatusValue(PICTURE_REJECTED);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue discarded() {
        return new PictureStatusValue(PICTURE_DISCARDED);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue inUsed() {
        return new PictureStatusValue(PICTURE_IN_USED);
    }
}
