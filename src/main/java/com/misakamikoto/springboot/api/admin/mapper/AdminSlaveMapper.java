package com.misakamikoto.springboot.api.admin.mapper;

import com.misakamikoto.springboot.api.admin.dto.Admin;
import com.misakamikoto.springboot.api.config.database.mapper.SlaveMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@SlaveMapper
public interface AdminSlaveMapper {
    List<Admin> getExample();
}
