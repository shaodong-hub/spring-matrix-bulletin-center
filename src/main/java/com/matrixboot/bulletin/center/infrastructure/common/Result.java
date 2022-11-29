package com.matrixboot.bulletin.center.infrastructure.common;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * create in 2022/10/23 20:12
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Data
public class Result<T> {

    private static final String SUCCESS_CODE = "00000";
    private static final String FAILURE_CODE = "10000";

    private String code;

    private T data;

    public static <T> @NotNull Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        return result;
    }
    public static <T> @NotNull Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setCode(SUCCESS_CODE);
        return result;
    }

    public static <T> @NotNull Result<T> failure(String message) {
        Result<T> result = new Result<>();
        result.setCode(FAILURE_CODE);
        return result;
    }

}
