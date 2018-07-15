package com.misakamikoto.springboot.api.services.book;

import com.misakamikoto.springboot.api.ApiApplication;
import com.misakamikoto.springboot.api.services.account.LoginControllerTest;
import com.misakamikoto.springboot.api.services.account.dto.Login;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
    // 히스토리 기능은 로그인 세션이 없으면 동작하지 않는다. 때문에 세션이 있는 경우, 없는 경우 두 가지 테스트를 한다.
    // test1NoSession -> java 책 검색 (정확도, 1페이지, 20개) : Expected redirect logout
    // test2NoSession -> javascript 책 검색 (정확도, 1페이지, 20개) : Expected redirect logout
    // test3NoSession -> 히스토리 id 정렬 조회 : Expected redirect logout
    // test4NoSession -> 히스토리 query 정렬 조회 : Expected redirect logout
    // test5NoSession -> 히스토리 datetime 정렬 조회 : Expected redirect logout
    // test6NoSession -> 히스토리 2건을 세이브 후 히스토리 id가 1인 히스토리 삭제 : Expected redirect logout
    // test1Session -> java 책 검색 (정확도, 1페이지, 20개) : Expected isOK
    // test2Session -> javascript 책 검색 (정확도, 1페이지, 20개) : Expected isOK
    // test3Session -> 히스토리 id 정렬 조회 : Expected isOK
    // test4Session -> 히스토리 query 정렬 조회 : Expected isOK
    // test5Session -> 히스토리 datetime 정렬 조회 : Expected isOK
    // test6Session -> 히스토리 2건을 세이브 후 히스토리 id가 1인 히스토리 삭제 : Expected isOK

    @Test
    public void test1NoSession() throws Exception {
        this.mockMvc.perform(get("/v1/book/search/books?query=java&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test2NoSession() throws Exception {
        this.mockMvc.perform(get("/v1/book/search/books?query=javascript&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test3NoSession() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/id")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test4NoSession() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/query")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test5NoSession() throws Exception {
        this.mockMvc.perform(get("/v1/history/aa/order/datetime")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test6NoSession() throws Exception {
        this.mockMvc.perform(delete("/v1/history/aa?ids=1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void test1Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/book/search/books?query=java&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test2Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/book/search/books?query=javascript&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test3Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/history/aa/order/id")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test4Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/history/aa/order/query")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test5Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/history/aa/order/datetime")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test6Session() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(delete("/v1/history/aa?ids=1")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
