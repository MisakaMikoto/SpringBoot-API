package com.misakamikoto.springboot.api.services.book.dto;

import lombok.Data;

@Data
public class Pagination {
    int totalCount;
    int pageableCount;
    boolean isEnd;
    // is default
    int pageCount = 10;
}
