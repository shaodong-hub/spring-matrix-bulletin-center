package com.matrixboot.bulletin.center.interfaces.facade;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * create in 2022/11/29 00:07
 *
 * @author shishaodong
 * @version 0.0.1
 */
@WebMvcTest(BulletinManageFacade.class)
@AutoConfigureRestDocs
class BulletinFacadeTest {

    @Resource
    private MockMvc mvc;

    @Test
    void findAll() throws Exception {
        this.mvc.perform(get("/bulletins").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("list-bulletins"));
    }
}