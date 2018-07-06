package com.misakamikoto.springboot.api.book.history.controller;

import com.misakamikoto.springboot.api.book.history.dto.BookHistory;
import com.misakamikoto.springboot.api.book.history.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookHistoryController {

    @Autowired
    BookHistoryService bookHistoryService;


    @GetMapping("bookHistories")
    public List<BookHistory> getList() {
        return new ArrayList<BookHistory>();
    }
}
