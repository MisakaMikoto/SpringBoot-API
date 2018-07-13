package com.misakamikoto.springboot.api.services.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class Search {
    List<Book> books;
    Pagination pagination;
}
