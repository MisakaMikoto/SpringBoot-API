package com.misakamikoto.springboot.api.book.history.controller;

import com.misakamikoto.springboot.api.book.history.dto.SearchHistory;
import com.misakamikoto.springboot.api.book.history.service.SearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchHistoryController {

    @Autowired
    SearchHistoryService searchHistoryService;

    @GetMapping("/histories")
    public ResponseEntity getList(@RequestParam("memberId") String memberId) {
        List<SearchHistory> searchHistories = this.searchHistoryService.getListById(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/histories/query")
    public ResponseEntity getListByQuery(@RequestParam("memberId") String memberId) {
        List<SearchHistory> searchHistories = this.searchHistoryService.getListByQuery(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/histories/datetime")
    public ResponseEntity getListByDatetime(@RequestParam("memberId") String memberId) {
        List<SearchHistory> searchHistories = this.searchHistoryService.getListByDatetime(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @DeleteMapping("/histories")
    public ResponseEntity deleteHistories(@RequestParam("memberId") String memberId, @RequestParam("ids") String ids) {
        List<SearchHistory> searchHistories = this.searchHistoryService.deleteHistories(memberId, ids);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }
}
