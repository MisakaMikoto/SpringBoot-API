package com.misakamikoto.springboot.api.services.book;

import com.misakamikoto.springboot.api.ApiApplication;
import com.misakamikoto.springboot.api.services.book.controller.BookHistoryController;
import com.misakamikoto.springboot.api.services.book.service.BookHistoryService;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitPlatform.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes={ApiApplication.class})
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookHistoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookHistoryController bookHistoryController;

    @Autowired
    BookHistoryService bookHistoryService;

    // ***** TEST CASE *****
    // test1 -> java 책 검색 (정확도, 1페이지, 20개)
    // test2 -> javascript 책 검색 (정확도, 1페이지, 20개)
    // test3 -> 히스토리 id 정렬 조회
    // test4 -> 히스토리 query 정렬 조회
    // test5 -> 히스토리 datetime 정렬 조회
    // test6 -> 히스토리 2건을 세이브 후 히스토리 id가 1인 히스토리 삭제

    @Test
    public void test1() throws Exception {
        this.mockMvc.perform(get("/v1/book/search/books?query=java&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test2() throws Exception {
        this.mockMvc.perform(get("/v1/book/search/books?query=javascript&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test3() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/id")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test4() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/query")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test5() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/datetime")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test6() throws Exception {
        this.mockMvc.perform(delete("/v1/history/aa?ids=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
