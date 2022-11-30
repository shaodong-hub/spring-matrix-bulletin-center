package com.matrixboot.bulletin.center.infrastructure.common.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;

/**
 * create in 2022/11/30 20:40
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StatusValue {

    private Integer status;

    @Contract(" -> new")
    public static @NotNull StatusValue unaudited() {
        return new StatusValue(0);
    }

    @Contract(" -> new")
    public static @NotNull StatusValue audited() {
        return new StatusValue(0);
    }

    @Contract(" -> new")
    public static @NotNull StatusValue reject() {
        return new StatusValue(-1);
    }
}
