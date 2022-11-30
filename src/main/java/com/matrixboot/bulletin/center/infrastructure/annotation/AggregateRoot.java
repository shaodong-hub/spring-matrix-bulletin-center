package com.matrixboot.bulletin.center.infrastructure.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create in 2022/11/30 19:42
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AggregateRoot {
}
