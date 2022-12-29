package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.entity.MatrixUserInfo;
import com.matrixboot.bulletin.center.domain.repository.IBulletinInfoRepository;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * create in 2022/12/16 17:33
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@ActiveProfiles("junit")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("帖子管理")
@SpringBootTest
@Testcontainers
class BulletinServiceTest {

    @Resource
    private BulletinUserService bulletinService;

    @Resource
    private IBulletinInfoRepository bulletinInfoRepository;

    @Resource
    private IPictureRepository pictureRepository;

    @Container
    static MongoDBContainer mongo = new MongoDBContainer("mongo:6.0.3");
    @Container
    public GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis:6.0.16")).withExposedPorts(6379);

    @DynamicPropertySource
    static void mongoProperties(@NotNull DynamicPropertyRegistry registry) {
        log.info("---- spring.data.mongodb.uri {}", mongo.getReplicaSetUrl());
        registry.add("spring.data.mongodb.uri", () -> mongo.getReplicaSetUrl());

    }


    @AfterEach
    void afterEach() {
        bulletinInfoRepository.deleteAll();
    }

    @Order(1)
    @RepeatedTest(10)
    @DisplayName("查找列表")
    void findCurrentBulletins() {
        var page = bulletinService.findCurrentBulletins(new MatrixUserInfo("1"), Pageable.unpaged());
        Assertions.assertEquals(0, page.getTotalElements());
    }

    @Order(2)
    @Test
    @DisplayName("新增帖子")
    void create() {
        var title = "junit_title";
        var content = "junit_content";
        var result = bulletinService.create(new MatrixUserInfo("1"), new BulletinCreateCommand(title, content, Sets.set("1", "2", "3")));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Order(3)
    @Test
    @DisplayName("修改帖子")
    void update() {
        var title = "junit_update_title";
        var content = "junit_update_content";
        var result = bulletinService.update(new MatrixUserInfo("1"), new BulletinUpdateCommand("1", title, content, Sets.set("10", "9", "8")));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Order(4)
    @Test
    @DisplayName("删除帖子")
    void delete() {
        BulletinResult result = bulletinService.delete(new MatrixUserInfo("1"), new BulletinDeleteCommand("1"));
        Assertions.assertEquals("1", result.id());
    }
}