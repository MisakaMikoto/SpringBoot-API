package com.misakamikoto.springboot.api.biz.service;

import com.misakamikoto.springboot.api.biz.mapper.BizMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BizService {

    @Autowired
    BizMapper bizMapper;

    public String getService() {
        return "biz";
    }
}
