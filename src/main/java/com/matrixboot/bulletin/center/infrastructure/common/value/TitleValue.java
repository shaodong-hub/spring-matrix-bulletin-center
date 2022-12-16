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
public class TitleValue implements Serializable {

    @Serial
    private static final long serialVersionUID = 6904750358705203424L;

    private String title;

}
