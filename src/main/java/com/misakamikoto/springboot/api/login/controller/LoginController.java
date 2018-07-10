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

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(path = "/signin")
    public ResponseEntity signIn(@RequestBody Member member) {
        Login login = this.loginService.signIn(member);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity login(@RequestBody Member member, HttpSession httpSession) {
        Login login = this.loginService.logIn(member);
        httpSession.setAttribute("login", login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @PostMapping(path = "/session/login")
    public ResponseEntity login(@ModelAttribute("login") Login sessionLogin, HttpSession httpSession) {
        Login login = this.loginService.loginToSession(sessionLogin);
        httpSession.setAttribute("login", login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    @GetMapping(path = "/logout")
    public ResponseEntity logout() {
        Login login = new Login();
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
