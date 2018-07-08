package com.misakamikoto.springboot.api.login.controller;

import com.misakamikoto.springboot.api.login.dto.Login;
import com.misakamikoto.springboot.api.login.dto.Member;
import com.misakamikoto.springboot.api.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/account/signin")
    public ResponseEntity signIn(@RequestBody Member member) {
        Login login = this.loginService.signIn(member);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping(path = "/account/login")
    public ResponseEntity login(@RequestBody Member member) {
        Login login = this.loginService.logIn(member);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
