package com.misakamikoto.springboot.api.book.history.service;

import com.misakamikoto.springboot.api.book.history.dto.BookHistory;
import com.misakamikoto.springboot.api.book.history.repository.BookHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookHistoryService {

    @Autowired
    BookHistoryRepository bookHistoryRepository;

    public void getList(){

    }

    public void save(BookHistory bookHistory) {
        bookHistoryRepository.save(bookHistory);
    }
}
