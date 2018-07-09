package com.misakamikoto.springboot.api.book.history.controller;

import com.misakamikoto.springboot.api.book.history.dto.SearchHistory;
import com.misakamikoto.springboot.api.book.history.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping("/histories")
    public ResponseEntity getList() {
        List<SearchHistory> searchHistories = this.searchHistoryService.getList();
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }
}
