package com.misakamikoto.springboot.api.index.controller;

import com.misakamikoto.springboot.api.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/")
    public String main() {
        return "index";
    }
}
