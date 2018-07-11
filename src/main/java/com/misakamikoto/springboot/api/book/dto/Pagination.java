package com.misakamikoto.springboot.api.book.dto;

import lombok.Data;

/**
 * Created by ryuha on 2018. 7. 11..
 */
@Data
public class Pagination {
    int totalCount;
    int pageableCount;
    boolean isEnd;
}
