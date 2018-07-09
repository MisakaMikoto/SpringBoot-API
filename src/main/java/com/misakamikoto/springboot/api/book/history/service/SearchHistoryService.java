package com.misakamikoto.springboot.api.book.history.service;

import com.google.common.collect.Lists;
import com.misakamikoto.springboot.api.book.history.dto.SearchHistory;
import com.misakamikoto.springboot.api.book.history.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class SearchHistoryService {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistory> getList(){
        return Lists.newArrayList(this.searchHistoryRepository.findAll());
    }

    public void save(String query) {
        SearchHistory bookHistory = new SearchHistory();
        bookHistory.setQuery(query);
        bookHistory.setDatetime(this.createCurrentDateTime());

        this.searchHistoryRepository.save(bookHistory);
    }

    private String createCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(cal.getTime());
    }
}
