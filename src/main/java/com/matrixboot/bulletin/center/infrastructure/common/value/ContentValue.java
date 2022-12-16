package com.matrixboot.bulletin.center.infrastructure.common.value;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serial;
import java.io.Serializable;

/**
 * create in 2022/11/30 20:39
 *
 * @author shishaodong
 * @version 0.0.1
 */

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ContentValue implements Serializable {

    @Serial
    private static final long serialVersionUID = -6819889644046389359L;

    private String content;

}
