package com.misakamikoto.springboot.api.book.history.service;

import com.misakamikoto.springboot.api.book.history.dto.SearchHistory;
import com.misakamikoto.springboot.api.book.history.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SearchHistoryService {

    @Autowired
    SearchHistoryRepository searchHistoryRepository;

    public List<SearchHistory> getListById(String memberId){
        return StreamSupport.stream(this.searchHistoryRepository.findAll().spliterator(), false)
                .filter(searchHistory -> memberId.equals(searchHistory.getMemberId())).collect(Collectors.toList());
    }

    public List<SearchHistory> getListByQuery(String memberId){
        List<SearchHistory> searchHistories = this.getListById(memberId);
        searchHistories.sort(Comparator.comparing(SearchHistory::getQuery));
        return searchHistories;
    }

    public List<SearchHistory> getListByDatetime(String memberId){
        List<SearchHistory> searchHistories = this.getListById(memberId);
        searchHistories.sort(Comparator.comparing(SearchHistory::getDatetime));
        return searchHistories;
    }

    public void save(String query, String memberId) {
        SearchHistory bookHistory = new SearchHistory();
        bookHistory.setQuery(query);
        bookHistory.setMemberId(memberId);
        bookHistory.setDatetime(this.createCurrentDateTime());

        this.searchHistoryRepository.save(bookHistory);
    }

    public List<SearchHistory> deleteHistories(String memberId, String historyIds) {
        for(String id : historyIds.split(",")) {
            this.searchHistoryRepository.deleteById(Long.valueOf(id));
        }
        return this.getListById(memberId);
    }

    private String createCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(cal.getTime());
    }
}
