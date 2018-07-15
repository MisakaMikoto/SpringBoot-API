package com.misakamikoto.springboot.api.services.account;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.misakamikoto.springboot.api.ApiApplication;
import com.misakamikoto.springboot.api.services.account.controller.LoginController;
import com.misakamikoto.springboot.api.services.account.dto.Member;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(JUnitPlatform.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes={ApiApplication.class})
@AutoConfigureMockMvc
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LoginController loginController;


    // ***** TEST CASE *****
    // test1 -> id, name password 가 aa 인 유저 생성
    // test2 -> aa 유저 로그인
    // test3 -> aa 유저의 암호를 aaa 로 수정
    // test4 -> 로그 아웃

    @Test
    public void test1( ) throws Exception {
        String json = createDummyMember("aa", "aa", "aa", "aa");
        this.mockMvc.perform(post("/v1/account/signin")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test2( ) throws Exception {
        String json = createDummyMember("aa", "aa", "aa", "aa");
        this.mockMvc.perform(post("/v1/account/login")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test3( ) throws Exception {
        String json = createDummyMember("aa", "aa", "aa", "aaa");
        this.mockMvc.perform(put("/v1/account/modify")
                .content(json).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test4( ) throws Exception {
        this.mockMvc.perform(get("/v1/account/logout"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String createDummyMember(String id, String name, String password, String modifyPassword) {
        Gson gson = new GsonBuilder().create();

        Member member = new Member();
        member.setId(id);
        member.setName(name);
        member.setPassword(password);
        member.setModifyPassword(modifyPassword);

        return gson.toJson(member);
    }
}
