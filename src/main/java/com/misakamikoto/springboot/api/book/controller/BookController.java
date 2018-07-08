package com.misakamikoto.springboot.api.book.controller;

import com.misakamikoto.springboot.api.book.dto.Book;
import com.misakamikoto.springboot.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public ResponseEntity search(@RequestParam("query") String query) throws IOException {
        List<Book> books = bookService.getBooks(query);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
