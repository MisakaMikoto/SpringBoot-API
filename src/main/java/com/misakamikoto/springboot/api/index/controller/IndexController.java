package com.misakamikoto.springboot.api.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value={"/", "/signin", "/search", "/history", "/bookmark"})
    public String main() {
        return "index";
    }
}
