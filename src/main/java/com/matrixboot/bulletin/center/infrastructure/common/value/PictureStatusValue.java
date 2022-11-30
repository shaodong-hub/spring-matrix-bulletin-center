package com.matrixboot.bulletin.center.infrastructure.common.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;

/**
 * create in 2022/11/30 23:54
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PictureStatusValue {

    private Integer status;

    @Contract(" -> new")
    public static @NotNull PictureStatusValue unaudited() {
        return new PictureStatusValue(0);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue audited() {
        return new PictureStatusValue(0);
    }

    @Contract(" -> new")
    public static @NotNull PictureStatusValue reject() {
        return new PictureStatusValue(-1);
    }
}
