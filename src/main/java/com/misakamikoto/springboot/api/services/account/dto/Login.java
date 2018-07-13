package com.misakamikoto.springboot.api.services.account.dto;

import lombok.Data;

@Data
public class Login {
    boolean status;
    String message;
    String memberId;
    String memberName;
}
