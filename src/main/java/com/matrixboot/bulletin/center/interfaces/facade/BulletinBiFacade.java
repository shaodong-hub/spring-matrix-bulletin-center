package com.matrixboot.bulletin.center.interfaces.facade;

import com.matrixboot.bulletin.center.application.service.BulletinBiService;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinIdCommand;
import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinStatisticsResult;
import com.matrixboot.bulletin.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.transaction.event.TransactionPhase.BEFORE_COMMIT;

/**
 * create in 2022/12/16 21:35
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Controller
@RequiredArgsConstructor
public class BulletinBiFacade {

    private final BulletinBiService service;

    @GetMapping("/bulletin/statistics/{id}")
    public Result<BulletinStatisticsResult> findById(@PathVariable Long id) {
        return Result.success();
    }

    @PostMapping("/bulletin/statistics/view")
    public Result<BulletinStatisticsResult> increaseView(@RequestBody BulletinIdCommand command) {
        service.increaseView(command);
        return Result.success();
    }

    @PostMapping("/bulletin/statistics/favorite")
    public Result<BulletinStatisticsResult> increaseFavorite(@RequestBody BulletinIdCommand command) {
        service.increaseFavorite(command);
        return Result.success();
    }

    @TransactionalEventListener(value = BulletinCreateEvent.class, phase = BEFORE_COMMIT)
    public void bulletinCreateListener(BulletinCreateEvent event) {
        service.bulletinBiCreate(event);
    }
}
