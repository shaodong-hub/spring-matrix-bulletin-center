//package com.matrixboot.bulletin.center.application.service;
//
//import com.matrixboot.bulletin.center.domain.entity.BulletinStatisticsEntity;
//import com.matrixboot.bulletin.center.domain.repository.IBulletinStatisticsRepository;
//import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinIdCommand;
//import com.matrixboot.bulletin.center.infrastructure.common.event.BulletinCreateEvent;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//
//
///**
// * create in 2022/12/16 20:02
// *
// * @author shishaodong
// * @version 0.0.1
// */
//@Service
//@Validated
//@RequiredArgsConstructor
//public class BulletinBiService {
//
//    private final IBulletinStatisticsRepository repository;
//
//    @Transactional(rollbackFor = Exception.class, timeout = 1)
//    public void bulletinBiCreate(@NotNull BulletinCreateEvent event) {
//        if (!repository.existsByBulletinId(event.bulletinId())) {
//            var transientBulletin = new BulletinStatisticsEntity(event.bulletinId());
//            repository.save(transientBulletin);
//        }
//    }
//
//    @Async
//    public void increaseView(@Valid @NotNull BulletinIdCommand command) {
//        repository.increaseView(command.id());
//    }
//
//    @Async
//    public void increaseLike(@Valid @NotNull BulletinIdCommand command) {
//        repository.increaseLike(command.id());
//    }
//
//    @Async
//    public void increaseFavorite(@Valid @NotNull BulletinIdCommand command) {
//        repository.increaseFavorite(command.id());
//    }
//}
