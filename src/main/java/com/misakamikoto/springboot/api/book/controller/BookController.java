package com.misakamikoto.springboot.api.book.controller;

import com.misakamikoto.springboot.api.book.dto.Book;
import com.misakamikoto.springboot.api.book.history.service.SearchHistoryService;
import com.misakamikoto.springboot.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    SearchHistoryService bookSearchHistoryService;

    @GetMapping("/books")
    public ResponseEntity search(@RequestParam("query") String query, @RequestParam("memberId") String memberId) throws IOException {
        this.bookSearchHistoryService.save(query, memberId);
        List<Book> books = bookService.getBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
