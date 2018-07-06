package com.misakamikoto.springboot.api.biz.controller;

import com.misakamikoto.springboot.api.biz.service.BizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BizController {

    @Autowired
    BizService bizService;

    @GetMapping("/biz/get")
    public String getBiz() {
        return bizService.getService();
    }
}
