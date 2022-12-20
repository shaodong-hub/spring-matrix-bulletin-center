package com.matrixboot.bulletin.center.application.task;

import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.infrastructure.common.value.PictureStatusValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * create in 2022/11/30 00:22
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PictureExpireTask {

    private final IPictureRepository repository;

    @Scheduled(cron = "@hourly")
    public void task() {
        var yesterday = LocalDateTime.now().minusDays(1L);
        long count = repository.deleteAllByCreatedDateBeforeAndStatusIs(yesterday, PictureStatusValue.discarded());
        log.info("{}", count);
    }
}
