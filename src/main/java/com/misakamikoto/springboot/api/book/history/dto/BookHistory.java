package com.misakamikoto.springboot.api.book.history.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BookHistory {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    String id;

    @Column
    String query;

    @Column
    String datetime;
}
