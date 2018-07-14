package com.misakamikoto.springboot.api.services.account.controller;

import com.misakamikoto.springboot.api.services.account.dto.Login;
import com.misakamikoto.springboot.api.services.account.dto.Member;
import com.misakamikoto.springboot.api.services.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/v1/account/signin")
    public ResponseEntity signIn(@RequestBody Member member) {
        Login login = this.loginService.signIn(member);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping(path = "/v1/account/login")
    public ResponseEntity login(@RequestBody Member member, HttpSession httpSession) {
        Login login = this.loginService.logIn(member);
        httpSession.setAttribute("login", login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PutMapping(path = "/v1/account/modify")
    public ResponseEntity modify(@RequestBody Member member) {
        Login login = this.loginService.modify(member);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @GetMapping(path = "/v1/account/logout")
    public ResponseEntity logout() {
        Login login = new Login();
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
