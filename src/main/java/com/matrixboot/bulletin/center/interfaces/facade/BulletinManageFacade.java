package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinService;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.common.Result;
import com.matrixboot.bulletin.common.annotation.AuthPrincipal;
import com.matrixboot.bulletin.common.core.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * create in 2022/11/29 00:06
 *
 * @author shishaodong
 * @version 0.0.1
 */
@RestController
@RequiredArgsConstructor
public class BulletinManageFacade {

    private final BulletinService service;

    /**
     * 当前用户所有的帖子
     *
     * @param userInfo UserInfo
     * @param pageable Pageable
     * @return Page
     */
    @GetMapping("current/bulletins")
    public Result<Page<BulletinResult>> findCurrentBulletins(@AuthPrincipal UserInfo userInfo, @PageableDefault Pageable pageable) {
        return Result.success(service.findCurrentBulletins(userInfo, pageable));
    }

    /**
     * 新建
     *
     * @param command BulletinCreateCommand
     * @return BulletinResult
     */
    @PostMapping("bulletin")
    public Result<BulletinResult> create(@RequestBody BulletinCreateCommand command) {
        return Result.success(service.create(command));
    }

    /**
     * 更新
     *
     * @param command BulletinUpdateCommand
     * @return BulletinResult
     */
    @PutMapping("bulletin")
    public Result<BulletinResult> update(@RequestBody BulletinUpdateCommand command) {
        return Result.success(service.update(command));
    }

    /**
     * 删除
     *
     * @param command BulletinDeleteCommand
     * @return BulletinResult
     */
    @DeleteMapping("bulletin")
    public Result<BulletinResult> delete(@RequestBody BulletinDeleteCommand command) {
        return Result.success(service.delete(command));
    }
}
