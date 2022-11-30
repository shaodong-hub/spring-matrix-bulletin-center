package com.matrixboot.bulletin.center.interfaces.facade;

import cn.hutool.core.io.IoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create in 2022/11/30 19:51
 *
 * @author shishaodong
 * @version 0.0.1
 */
@SpringBootTest
@ActiveProfiles("junit")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class BulletinManageFacadeTest {

    @Resource
    private MockMvc mvc;

    @Value("classpath:json/BulletinCreateCommand.json")
    private org.springframework.core.io.Resource bulletinCreateCommand;


    @Test
    void findCurrentBulletins() throws Exception {
        mvc.perform(get("/current/bulletins").accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
        var inputStream = bulletinCreateCommand.getInputStream();
        var read = IoUtil.read(inputStream, StandardCharsets.UTF_8);
        mvc.perform(post("/bulletin")
                        .accept(APPLICATION_JSON_VALUE)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(read))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        mvc.perform(put("/bulletin").accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {

    }
}