package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.PictureService;
import com.matrixboot.bulletin.center.infrastructure.annotation.AuthPrincipal;
import com.matrixboot.bulletin.center.infrastructure.common.Result;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinDeleteEvent;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinModifyEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.PictureResult;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
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

    private final PictureService service;

    /**
     * 上传图片
     *
     * @param userInfo UserInfo
     * @param command  PictureCreateCommand
     * @return Result
     */
    @PostMapping("/picture")
    public Result<PictureResult> createPicture(@AuthPrincipal UserInfo userInfo, PictureCreateCommand command) {
        return Result.success(service.createPicture(userInfo, command));
    }

    /**
     * 删除图片
     *
     * @param userInfo UserInfo
     * @param command  PictureDeleteCommand
     * @return Result
     */
    @DeleteMapping("/picture")
    public Result<PictureResult> deletePicture(@AuthPrincipal UserInfo userInfo, PictureDeleteCommand command) {
        return Result.success(service.deletePicture(userInfo, command));
    }

    /**
     * 帖子更新事件
     *
     * @param event BulletinModifyEvent
     */
    @EventListener(BulletinModifyEvent.class)
    public void bulletinCreate(BulletinModifyEvent event) {
        service.bulletinCreate(event);
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
