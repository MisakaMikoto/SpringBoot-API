package com.misakamikoto.springboot.api.book.mark.controller;

import com.misakamikoto.springboot.api.book.mark.dto.BookMark;
import com.misakamikoto.springboot.api.book.mark.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookMarkController {

    @Autowired
    BookMarkService bookMarkService;

    @GetMapping("/bookmarks")
    public ResponseEntity getListById(@RequestParam("memberId") String memberId) {
        List<BookMark> bookMarks = this.bookMarkService.getListById(memberId);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);

    }

    @PostMapping("/bookmark")
    public ResponseEntity save(@RequestBody BookMark bookMark) {
        BookMark saveBookMark = this.bookMarkService.save(bookMark);
        return new ResponseEntity<>(saveBookMark, HttpStatus.OK);
    }
}
