package com.misakamikoto.springboot.api.book.history.controller;

import com.misakamikoto.springboot.api.book.history.dto.BookHistory;
import com.misakamikoto.springboot.api.book.history.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookHistoryController {

    @Autowired
    BookHistoryService bookHistoryService;


    @GetMapping("/bookHistories")
    public ResponseEntity getList() {
        return new ResponseEntity<>("aa", HttpStatus.OK);
    }

    @PostMapping("/bookHistory")
    public ResponseEntity save(@RequestBody BookHistory bookHistory) {
        this.bookHistoryService.save(bookHistory);
        return new ResponseEntity<>("aa", HttpStatus.OK);
    }
}
