package com.misakamikoto.springboot.api.services.book.controller;

import com.misakamikoto.springboot.api.services.book.dto.History;
import com.misakamikoto.springboot.api.services.book.service.BookSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookSearchHistoryController {

    @Autowired
    BookSearchHistoryService bookSearchHistoryService;

    @GetMapping("/v1/history/{memberId}/id")
    public ResponseEntity getList(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookSearchHistoryService.getListById(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/v1/history/{memberId}/query")
    public ResponseEntity getListByQuery(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookSearchHistoryService.getListByQuery(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/v1/history/{memberId}/datetime")
    public ResponseEntity getListByDatetime(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookSearchHistoryService.getListByDatetime(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @DeleteMapping("/v1/history/{memberId}")
    public ResponseEntity deleteHistories(@PathVariable("memberId") String memberId, @RequestParam("ids") String ids) {
        List<History> searchHistories = this.bookSearchHistoryService.deleteHistories(memberId, ids);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }
}
