package com.misakamikoto.springboot.api.login.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Member {

    public Member(){}

    @Id
    private String id;

    @Column
    private String password;

    @Column
    private String name;
}
