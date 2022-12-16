package com.matrixboot.bulletin.center.infrastructure.common.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2022/12/16 20:06
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsValue implements Serializable {

    @Serial
    private static final long serialVersionUID = -3627507122580385690L;

    private Long view;

    private Long favorite;

    @Contract(" -> new")
    public static @NotNull StatisticsValue defaultStatisticsValue() {
        return new StatisticsValue(0L, 0L);
    }


}
