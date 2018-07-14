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

    @GetMapping("/v1/bookmark/{memberId}/order/id")
    public ResponseEntity getListOrderId(@PathVariable("memberId") String memberId) {
        List<Bookmark> bookMarks = this.bookmarkService.getListOrderId(memberId);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);
    }

    @GetMapping("/v1/bookmark/{memberId}/order/title")
    public ResponseEntity getListOrderTitle(@PathVariable("memberId") String memberId) {
        List<Bookmark> bookMarks = this.bookmarkService.getListOrderTitle(memberId);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);
    }

    @GetMapping("/v1/bookmark/{memberId}/order/status")
    public ResponseEntity getListOrderStatus(@PathVariable("memberId") String memberId) {
        List<Bookmark> bookMarks = this.bookmarkService.getListOrderStatus(memberId);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);
    }

    @PostMapping("/v1/bookmark/{memberId}")
    public ResponseEntity save(@PathVariable("memberId") String memberId, @RequestBody Bookmark bookmark) {
        bookmark.setMemberId(memberId);
        Bookmark saveBookMark = this.bookmarkService.save(bookmark);
        return new ResponseEntity<>(saveBookMark, HttpStatus.OK);
    }

    @GetMapping("/v1/bookmark/{memberId}/{isbn}")
    public ResponseEntity getListByIsbn(@PathVariable("memberId") String memberId, @PathVariable("isbn") String isbn) {
        List<Bookmark> bookMarks = this.bookmarkService.getListByIsbn(memberId, isbn);
        return new ResponseEntity<>(bookMarks, HttpStatus.OK);
    }

    @DeleteMapping("/v1/bookmark/{memberId}")
    public ResponseEntity deleteBookmarks(@PathVariable("memberId") String memberId, @RequestParam("ids") String ids) {
        List<Bookmark> bookmarks = this.bookmarkService.deleteBookmarks(memberId, ids);
        return new ResponseEntity<>(bookmarks, HttpStatus.OK);
    }
}
