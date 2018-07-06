package com.misakamikoto.springboot.api.admin.service;

import com.misakamikoto.springboot.api.admin.dto.Admin;
import com.misakamikoto.springboot.api.admin.mapper.AdminMasterMapper;
import com.misakamikoto.springboot.api.admin.mapper.AdminSlaveMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    AdminMasterMapper adminMasterMapper;

    @Autowired
    AdminSlaveMapper adminSlaveMapper;

    public String getMaster() {
        return "master";
    }
    public String getSlave() {
        return "slave";
    }

}
