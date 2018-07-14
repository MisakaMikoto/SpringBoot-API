package com.misakamikoto.springboot.api.services.book.controller;

import com.misakamikoto.springboot.api.services.book.dto.History;
import com.misakamikoto.springboot.api.services.book.service.BookHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookHistoryController {

    @Autowired
    BookHistoryService bookHistoryService;

    @GetMapping("/v1/history/{memberId}/order/id")
    public ResponseEntity getList(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookHistoryService.getListOrderId(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/v1/history/{memberId}/order/query")
    public ResponseEntity getListOrderQuery(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookHistoryService.getListOrderQuery(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @GetMapping("/v1/history/{memberId}/order/datetime")
    public ResponseEntity getListOrderDatetime(@PathVariable("memberId") String memberId) {
        List<History> searchHistories = this.bookHistoryService.getListOrderDatetime(memberId);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }

    @DeleteMapping("/v1/history/{memberId}")
    public ResponseEntity deleteHistories(@PathVariable("memberId") String memberId, @RequestParam("ids") String ids) {
        List<History> searchHistories = this.bookHistoryService.deleteHistories(memberId, ids);
        return new ResponseEntity<>(searchHistories, HttpStatus.OK);
    }
}
