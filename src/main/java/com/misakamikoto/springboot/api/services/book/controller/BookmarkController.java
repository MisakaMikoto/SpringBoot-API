package com.misakamikoto.springboot.api.services.book.controller;

import com.misakamikoto.springboot.api.services.book.dto.Bookmark;
import com.misakamikoto.springboot.api.services.book.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookmarkController {

    @Autowired
    BookmarkService bookmarkService;

    @GetMapping("/v1/book/bookmarks")
    public ResponseEntity getListById(@RequestParam("memberId") String memberId) {
        List<Bookmark> bookMarks = this.bookmarkService.getListById(memberId);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);

    }

    @PostMapping("/v1/bookmarks/{memberId}/")
    public ResponseEntity save(@PathVariable String memberId, @RequestBody Bookmark bookMark) {
        Bookmark saveBookMark = this.bookmarkService.save(bookMark);
        return new ResponseEntity<>(saveBookMark, HttpStatus.OK);
    }
}
