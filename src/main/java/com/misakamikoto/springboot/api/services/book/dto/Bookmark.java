package com.misakamikoto.springboot.api.services.book.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bookmark {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @Column
    String memberId;

    @Column
    String title;

    @Column
    String url;

    @Column
    String thumbnail;

    @Column
    String price;

    @Column
    Integer salePrice;

    @Column
    String status;

    @Column
    String isbn;
}
