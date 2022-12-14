package com.matrixboot.bulletin.center.infrastructure.common.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2022/11/30 20:53
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdValue implements Serializable {

    @Serial
    private static final long serialVersionUID = 3196605270913350489L;

    private Long userId;
}
