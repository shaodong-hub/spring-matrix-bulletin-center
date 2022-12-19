package com.matrixboot.bulletin.center.infrastructure.common.result;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * create in 2022/11/30 00:48
 *
 * @author shishaodong
 * @version 0.0.1
 */

public record PictureResult(Long id) implements Serializable {


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PictureResult that)) {
            return false;
        }
        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
