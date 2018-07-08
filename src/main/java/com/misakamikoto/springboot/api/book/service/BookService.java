package com.misakamikoto.springboot.api.book.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.misakamikoto.springboot.api.book.dto.Book;
import com.misakamikoto.springboot.api.common.rest.RestHttpGet;
import com.misakamikoto.springboot.api.config.database.properties.KakaoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class BookService {

    private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Autowired
    KakaoProperties kakaoProperties;

    public List<Book> getBooks(String query) throws IOException {
        RestHttpGet restHttpGet = new RestHttpGet();
        Map<String, String> headerMap = createSearchHeader.apply(kakaoProperties.getAppKey());
        String booksJson = restHttpGet.call(kakaoProperties.getSearchUrl(), query, headerMap);
        String parseBooksJson = parseBooksJson(booksJson);
        List<Book> books = gson.fromJson(parseBooksJson, new TypeToken<List<Book>>(){}.getType());
        return books;
    }

    private String parseBooksJson(String booksJson) {
        int start = booksJson.indexOf("[");
        int last = booksJson.lastIndexOf("]") + 1;

        return booksJson.substring(start, last);
    }

    Function<String, Map<String, String>> createSearchHeader = (appKey) -> {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "KakaoAK " + appKey);
        return headerMap;
    };
}
