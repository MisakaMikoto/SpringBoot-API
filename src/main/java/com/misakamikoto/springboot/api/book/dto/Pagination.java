package com.misakamikoto.springboot.api.book.dto;

import lombok.Data;

@Data
public class Pagination {
    int totalCount;
    int pageableCount;
    boolean isEnd;
    // is default
    int pageCount = 10;
}
