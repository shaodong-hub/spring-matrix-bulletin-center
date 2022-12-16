package com.matrixboot.bulletin.center.infrastructure.exception.handler;

import com.matrixboot.bulletin.center.infrastructure.exception.PictureNotFoundException;
import com.matrixboot.bulletin.common.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * create in 2022/11/30 00:40
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestControllerAdvice
public class PictureNotFoundExceptionHandler {

    @ExceptionHandler(PictureNotFoundException.class)
    public Result<String> bulletinNotFoundException(@NotNull PictureNotFoundException exception) {
        return Result.failure(exception.getMessage());
    }

}
