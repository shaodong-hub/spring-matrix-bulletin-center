package com.matrixboot.bulletin.center.interfaces.facade;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create in 2022/11/30 19:51
 *
 * @author shishaodong
 * @version 0.0.1
 */
@ActiveProfiles("junit")
@WebMvcTest(BulletinManageFacade.class)
@AutoConfigureRestDocs
class BulletinManageFacadeTest {

    @Resource
    private MockMvc mvc;

    @Test
    void findCurrentBulletins() throws Exception {
        mvc.perform(get("/current/bulletins").accept(APPLICATION_JSON_VALUE).contentType(APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void create() throws Exception {
    }

    @Test
    void update()  throws Exception{
    }

    @Test
    void delete() throws Exception {
    }
}