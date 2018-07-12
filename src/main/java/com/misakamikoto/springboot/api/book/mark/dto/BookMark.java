package com.misakamikoto.springboot.api.book.mark.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookMark {
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
    String salePrice;
}
