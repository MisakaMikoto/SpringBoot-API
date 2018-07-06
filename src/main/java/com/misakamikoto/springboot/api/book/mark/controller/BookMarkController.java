package com.misakamikoto.springboot.api.book.mark.controller;

import com.misakamikoto.springboot.api.book.mark.dto.BookMark;
import com.misakamikoto.springboot.api.book.mark.service.BookMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookMarkController {

    @Autowired
    BookMarkService bookMarkService;

    @GetMapping("/bookMarks")
    public List<BookMark> getList() {
        return new ArrayList<BookMark>();
    }
}
