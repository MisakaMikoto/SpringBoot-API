package com.misakamikoto.springboot.api.book.dto;

import lombok.Data;

import java.util.List;

@Data
public class Book {
    String title;
    String contents;
    String url;
    String isbn;
    String datetime;
    List<String> authors;
    String publisher;
    List<String> translators;
    String price;
    String salePrice;
    String saleYn;
    String category;
    String thumbnail;
    String barcode;
    String ebookBarcode;
    String status;
}
