package com.misakamikoto.springboot.api.book.dto;

import lombok.Data;

@Data
public class Book {
    String title;
    String contents;
    String url;
    String datetime;
}
