package com.misakamikoto.springboot.api.services.book.service;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.misakamikoto.springboot.api.services.book.dto.Book;
import com.misakamikoto.springboot.api.services.book.dto.Pagination;
import com.misakamikoto.springboot.api.services.book.dto.Search;
import com.misakamikoto.springboot.api.common.rest.RestHttpGet;
import com.misakamikoto.springboot.api.config.properties.KakaoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class BookSearchService {

    private Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    @Autowired
    KakaoProperties kakaoProperties;

    public Search getBooks(String query, String sort, int page, int size) throws IOException {
        RestHttpGet restHttpGet = new RestHttpGet();
        Map<String, String> headerMap = createSearchHeader.apply(kakaoProperties.getAppKey());

        String callUrl = this.createUrl(query, sort, page, size);
        String booksJson = restHttpGet.call(callUrl, headerMap);

        JsonObject booksJsonObject = this.parseJson(booksJson);

        List<Book> books = gson.fromJson(booksJsonObject.get("documents").toString(), new TypeToken<List<Book>>(){}.getType());
        Pagination pagination = gson.fromJson(booksJsonObject.get("meta").toString(), Pagination.class);

        Search search = new Search();
        search.setBooks(books);
        search.setPagination(pagination);

        return search;
    }

    private JsonObject parseJson(String booksJson) {
        JsonElement jsonElement = gson.fromJson (booksJson, JsonElement.class);
        return jsonElement.getAsJsonObject();
    }

    private String createUrl(String query, String sort, int page, int size) throws UnsupportedEncodingException {
        return kakaoProperties.getSearchUrl() + "?query=" + URLEncoder.encode(query, "UTF-8") + "&sort=" + sort + "&page="+ page + "&size=" + size;
    }

    Function<String, Map<String, String>> createSearchHeader = (appKey) -> {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Authorization", "KakaoAK " + appKey);
        return headerMap;
    };
}
