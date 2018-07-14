package com.misakamikoto.springboot.api.services.book.service;

import com.misakamikoto.springboot.api.services.book.dto.History;
import com.misakamikoto.springboot.api.services.book.repository.BookHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookHistoryService {

    @Autowired
    BookHistoryRepository bookHistoryRepository;

    public List<History> getListOrderId(String memberId){
        return StreamSupport.stream(this.bookHistoryRepository.findAll().spliterator(), false)
                .filter(searchHistory -> memberId.equals(searchHistory.getMemberId())).collect(Collectors.toList());
    }

    public List<History> getListOrderQuery(String memberId){
        List<History> searchHistories = this.getListOrderId(memberId);
        searchHistories.sort(Comparator.comparing(History::getQuery));
        return searchHistories;
    }

    public List<History> getListOrderDatetime(String memberId){
        List<History> searchHistories = this.getListOrderId(memberId);
        searchHistories.sort(Comparator.comparing(History::getDatetime));
        return searchHistories;
    }

    public void save(String query, String memberId) {
        History bookHistory = new History();
        bookHistory.setQuery(query);
        bookHistory.setMemberId(memberId);
        bookHistory.setDatetime(this.createCurrentDateTime());

        this.bookHistoryRepository.save(bookHistory);
    }

    public List<History> deleteHistories(String memberId, String historyIds) {
        for(String id : historyIds.split(",")) {
            this.bookHistoryRepository.deleteById(Long.valueOf(id));
        }
        return this.getListOrderId(memberId);
    }

    private String createCurrentDateTime() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(cal.getTime());
    }
}
