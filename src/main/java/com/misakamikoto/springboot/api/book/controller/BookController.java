package com.misakamikoto.springboot.api.book.controller;

import com.misakamikoto.springboot.api.book.dto.Book;
import com.misakamikoto.springboot.api.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    public List<Book> search() {
        return new ArrayList<Book>();
    }
}
