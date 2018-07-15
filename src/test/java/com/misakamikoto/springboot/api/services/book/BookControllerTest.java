package com.misakamikoto.springboot.api.services.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.misakamikoto.springboot.api.ApiApplication;
import com.misakamikoto.springboot.api.services.account.LoginControllerTest;
import com.misakamikoto.springboot.api.services.account.dto.Login;
import com.misakamikoto.springboot.api.services.account.dto.Member;
import com.misakamikoto.springboot.api.services.book.controller.BookController;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitPlatform.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes={ApiApplication.class})
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookController bookController;

    // ***** TEST CASE *****
    // 검색 기능은 로그인 세션이 없으면 동작하지 않는다. 때문에 세션이 있는 경우, 없는 경우 두 가지 테스트를 한다.
    // testNoSessionSearch -> Seesion 이 없는 상태에서 java 책검색(정확도, 1페이지, 20개) : Expected redirect logout
    // testSessionSearch -> Seesion 이 있는 상태에서 java 책검색(정확도, 1페이지, 20개) : Expected 200

    @Test
    public void testNoSessionSearch() throws Exception {
        this.mockMvc.perform(get("/v1/book/search/books?query=java&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(redirectedUrl("/v1/account/logout"));
    }

    @Test
    public void testSessionSearch() throws Exception {
        Login login = LoginControllerTest.createDummyLogin();
        this.mockMvc.perform(get("/v1/book/search/books?query=java&sort=accuracy&page=1&size=20&memberId=aa")
                .accept(MediaType.APPLICATION_JSON).sessionAttr("login", login))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
