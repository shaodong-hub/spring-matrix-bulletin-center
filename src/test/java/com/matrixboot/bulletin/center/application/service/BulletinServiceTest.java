package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinUpdateCommand;
import com.matrixboot.bulletin.common.core.UserInfo;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
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
@ActiveProfiles("junit")
@SpringBootTest
@Sql({"classpath:db/bulletin.sql", "classpath:db/picture.sql"})
@Rollback(value = false)
@SqlMergeMode(SqlMergeMode.MergeMode.MERGE)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BulletinServiceTest {

    @Resource
    private BulletinUserService bulletinService;

    @RepeatedTest(1)
    void findCurrentBulletins() {
        var page = bulletinService.findCurrentBulletins(new UserInfo(1L), Pageable.unpaged());
        Assertions.assertEquals(5, page.getTotalElements());
    }

    @Test
    void create() {
        var title = "junit_title";
        var content = "junit_content";
        var result = bulletinService.create(new BulletinCreateCommand(title, content, Sets.set(1L, 2L, 3L)));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Test
    void update() {
        var title = "junit_update_title";
        var content = "junit_update_content";
        var result = bulletinService.update(new BulletinUpdateCommand(1L, title, content, Sets.set(10L, 8L, 9L)));
        Assertions.assertEquals(title, result.title());
        Assertions.assertEquals(content, result.content());
    }

    @Test
    void delete() {
    }
}