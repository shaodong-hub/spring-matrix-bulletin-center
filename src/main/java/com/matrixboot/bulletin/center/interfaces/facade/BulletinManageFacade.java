package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinService;
import com.matrixboot.bulletin.center.infrastructure.annotation.AuthPrincipal;
import com.matrixboot.bulletin.center.infrastructure.common.Result;
import com.matrixboot.bulletin.center.infrastructure.common.UserInfo;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

//    @GetMapping("bulletins")
//    public Page<BulletinResult> findAll(BulletinQuery query, Pageable pageable) {
//        return service.findAll(query, pageable)
//    }

    /**
     * 当前用户所有的帖子
     *
     * @param userInfo UserInfo
     * @param pageable Pageable
     * @return Page
     */
    @GetMapping("current/bulletins")
    public Result<Page<BulletinResult>> findCurrentBulletins(@AuthPrincipal UserInfo userInfo, Pageable pageable) {
        return Result.success(service.findCurrentBulletins(userInfo, pageable));
    }

    /**
     * 新建
     *
     * @param userInfo UserInfo
     * @param command  BulletinCreateCommand
     * @return BulletinResult
     */
    @PostMapping("bulletin")
    public Result<BulletinResult> create(@AuthPrincipal UserInfo userInfo, @RequestBody BulletinCreateCommand command) {
        return Result.success(service.create(userInfo, command));
    }

    /**
     * 更新
     *
     * @param userInfo UserInfo
     * @param command  BulletinUpdateCommand
     * @return BulletinResult
     */
    @PutMapping("bulletin")
    public Result<BulletinResult> update(@AuthPrincipal UserInfo userInfo, @RequestBody BulletinUpdateCommand command) {
        return Result.success(service.update(userInfo, command));
    }

    /**
     * 删除
     *
     * @param userInfo UserInfo
     * @param command  BulletinDeleteCommand
     * @return BulletinResult
     */
    @DeleteMapping("bulletin")
    public Result<BulletinResult> delete(@AuthPrincipal UserInfo userInfo, @RequestBody BulletinDeleteCommand command) {
        return Result.success(service.delete(userInfo, command));
    }
}
