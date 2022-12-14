package com.matrixboot.bulletin.center.infrastructure.common.value;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2022/11/30 20:40
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BulletinStatusValue implements Serializable {

    @Serial
    private static final long serialVersionUID = 2229351581669919674L;

    private Integer status;

    @Contract(" -> new")
    public static @NotNull BulletinStatusValue closed() {
        return new BulletinStatusValue(11);
    }

    @Contract(" -> new")
    public static @NotNull BulletinStatusValue accepted() {
        return new BulletinStatusValue(1);
    }

    @Contract(" -> new")
    public static @NotNull BulletinStatusValue unaudited() {
        return new BulletinStatusValue(0);
    }

    @Contract(" -> new")
    public static @NotNull BulletinStatusValue rejected() {
        return new BulletinStatusValue(-1);
    }
}
