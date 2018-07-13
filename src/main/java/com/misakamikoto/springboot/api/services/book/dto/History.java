package com.misakamikoto.springboot.api.services.book.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class History {

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
