package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.PictureService;
import com.matrixboot.bulletin.center.infrastructure.annotation.AuthPrincipal;
import com.matrixboot.bulletin.center.infrastructure.common.Result;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.PictureDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
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
public class PictureFacade {

    private final PictureService service;

    @PostMapping("/picture")
    public Result<PictureResult> createPicture(@AuthPrincipal UserInfo userInfo, PictureCreateCommand command) {
        return Result.success(service.createPicture(userInfo, command));
    }

    @DeleteMapping("/picture")
    public Result<PictureResult> deletePicture(@AuthPrincipal UserInfo userInfo, PictureDeleteCommand command) {
        return Result.success(service.deletePicture(userInfo, command));
    }

    @EventListener(BulletinCreateEvent.class)
    public void bulletinCreate(BulletinCreateEvent event) {
        service.bulletinCreate(event);
    }
}
