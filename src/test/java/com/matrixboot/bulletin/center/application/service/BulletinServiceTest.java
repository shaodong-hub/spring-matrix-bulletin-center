package com.matrixboot.bulletin.center.application.service;

import com.matrixboot.bulletin.center.infrastructure.common.command.BulletinCreateCommand;
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
        var result = bulletinService.create(new BulletinCreateCommand("junit_title", "junit_content", Sets.set(1L, 2L, 3L)));
        Assertions.assertEquals("junit_title", result.title());
        Assertions.assertEquals("junit_content", result.content());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}