package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.domain.repository.IBulletinInfoRepository;
import com.matrixboot.bulletin.center.domain.repository.IPictureRepository;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinDeleteCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.result.BulletinResult;
import com.matrixboot.bulletin.common.core.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlMergeMode;

import javax.annotation.Resource;

/**
 * create in 2022/12/16 17:33
 *
 * @author shishaodong
 * @version 0.0.1
 */
@Slf4j
@ActiveProfiles("junit")
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Sql(scripts = {"classpath:db/bulletin.sql", "classpath:db/picture.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@SqlMergeMode(SqlMergeMode.MergeMode.OVERRIDE)
@DisplayName("帖子管理")
class BulletinServiceTest {

    @Resource
    private BulletinUserService bulletinService;

    @Resource
    private IBulletinInfoRepository bulletinInfoRepository;

    @Resource
    private IPictureRepository pictureRepository;

    @AfterEach
    void afterEach() {
        bulletinInfoRepository.deleteAll();
        pictureRepository.deleteAll();
    }

    @Order(1)
    @RepeatedTest(10)
    @DisplayName("查找列表")
    void findCurrentBulletins() {
        var page = bulletinService.findCurrentBulletins(new UserInfo(1L), Pageable.unpaged());
        Assertions.assertEquals(5, page.getTotalElements());
    }

    @Order(2)
    @Test
    @DisplayName("新增帖子")
    void create() {
        var title = "junit_title";
        var content = "junit_content";
        var result = bulletinService.create(new BulletinCreateCommand(title, content, Sets.set(1L, 2L, 3L)));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Order(3)
    @Test
    @DisplayName("修改帖子")
    void update() {
        var title = "junit_update_title";
        var content = "junit_update_content";
        var result = bulletinService.update(new BulletinUpdateCommand(1L, title, content, Sets.set(10L, 8L, 9L)));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Order(4)
    @Test
    @DisplayName("删除帖子")
    void delete() {
        BulletinResult result = bulletinService.delete(new BulletinDeleteCommand(1L));
        Assertions.assertEquals(1L, result.id());
    }
}