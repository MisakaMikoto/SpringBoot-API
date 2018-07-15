package com.misakamikoto.springboot.api.services.book;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.misakamikoto.springboot.api.ApiApplication;
import com.misakamikoto.springboot.api.services.book.controller.BookController;
import com.misakamikoto.springboot.api.services.book.dto.Bookmark;
import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(classes={ApiApplication.class})
@AutoConfigureMockMvc
@ActiveProfiles("local")
@FixMethodOrder
public class BookmarkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookController bookController;

    // ***** TEST CASE *****
    // test1 -> 북마크 등록 2건
    // test2 -> 북마크 id 정렬 조회
    // test3 -> 북마크 title 정렬 조회
    // test4 -> 북마크 status 정렬 조회
    // test5 -> 북마크 salePrice 정렬 조회
    // test6 -> 북마크 id가 1인 북마크 삭제

    @Test
    public void test1() throws Exception {
        String firstJson = createDummyBookmark("java","http://localhost:8080", "http://localhost:8080", "판매가능",
                10000, 8000, "12345");

        this.mockMvc.perform(post("/v1/bookmark/aa")
                .content(firstJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        String secondJson = createDummyBookmark("javascript","http://localhost:9090", "http://localhost:9090", "판매가능",
                12000, 7000, "678910");

        this.mockMvc.perform(post("/v1/bookmark/aa")
                .content(secondJson).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test2() throws Exception {
        this.mockMvc.perform(get("/v1/bookmark/aa/order/id")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test3() throws Exception {
        this.mockMvc.perform(get("/v1/bookmark/aa/order/title")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test4() throws Exception {
        this.mockMvc.perform(get("/v1/bookmark/aa/order/status")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void test5() throws Exception {
        this.mockMvc.perform(get("/v1/bookmark/aa/order/saleprice")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    private String createDummyBookmark(String title, String url, String thumbnail, String status, int price, int salePrice, String isbn) {
        Gson gson = new GsonBuilder().create();

        Bookmark bookmark = new Bookmark();
        bookmark.setTitle(title);
        bookmark.setUrl(url);
        bookmark.setThumbnail(thumbnail);
        bookmark.setStatus(status);
        bookmark.setPrice(price);
        bookmark.setSalePrice(salePrice);
        bookmark.setIsbn(isbn);

        return gson.toJson(bookmark);
    }
}
