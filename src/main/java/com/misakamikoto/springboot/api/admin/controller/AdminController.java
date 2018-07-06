package com.misakamikoto.springboot.api.admin.controller;

import com.misakamikoto.springboot.api.admin.service.AdminService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/admin/master/get")
    public String getMaster() {
        return adminService.getMaster();
    }

    @ApiOperation(value = "swagger Get parameter example")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "User's id",
                    required = true,
                    dataType = "String"),

            @ApiImplicitParam(
                    name = "name",
                    value = "User's name",
                    required = true,
                    dataType = "String")
    })
    @GetMapping("/admin/master/get/param")
    public String getMasterWithParameter(@ApiParam @RequestParam("id") String id, @RequestParam("name") String name ) {
        return adminService.getMaster();
    }

    @GetMapping("/admin/slave/get")
    public String getSlave() {
        return adminService.getSlave();
    }

    @PutMapping("/admin/master/put")
    public String putMaster() {
        return adminService.getMaster();
    }

    @PutMapping("/admin/slave/put")
    public String putSlave() {
        return adminService.getSlave();
    }

    @PostMapping("/admin/master/post")
    public String postMaster() {
        return adminService.getMaster();
    }

    @PostMapping("/admin/slave/post")
    public String postSlave() {
        return adminService.getSlave();
    }

    @DeleteMapping("/admin/master/delete")
    public String deleteMaster() {
        return adminService.getMaster();
    }

    @DeleteMapping("/admin/slave/delete")
    public String deleteSlave() {
        return adminService.getSlave();
    }
}
