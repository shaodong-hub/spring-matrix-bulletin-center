package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinPictureService;
import com.matrixboot.bulletin.center.domain.entity.MatrixUserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinDeleteEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import com.matrixboot.bulletin.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * create in 2022/11/30 00:46
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class PictureManageFacade {

    private final BulletinPictureService service;

    /**
     * 上传图片
     *
     * @param command PictureCreateCommand
     * @return Result
     */
    @PostMapping("/picture")
    public Result<PictureResult> createPicture(@AuthenticationPrincipal MatrixUserInfo user, PictureCreateCommand command) {
        return Result.success(service.createPicture(user, command));
    }

    /**
     * 删除图片
     *
     * @param command PictureDeleteCommand
     * @return Result
     */
    @DeleteMapping("/picture")
    public Result<PictureResult> deletePicture(@AuthenticationPrincipal MatrixUserInfo user, PictureDeleteCommand command) {
        return Result.success(service.deletePicture(user, command));
    }

    /**
     * 帖子删除事件
     *
     * @param event BulletinDeleteEvent
     */
    @EventListener(BulletinDeleteEvent.class)
    public void bulletinDelete(BulletinDeleteEvent event) {
        service.bulletinDelete(event);
    }
}
