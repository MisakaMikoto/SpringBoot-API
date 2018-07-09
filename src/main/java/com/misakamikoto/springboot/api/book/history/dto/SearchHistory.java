package com.misakamikoto.springboot.api.book.history.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SearchHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    @Column
    String memberId;

    @Column
    String query;

    @Column
    String datetime;
}
