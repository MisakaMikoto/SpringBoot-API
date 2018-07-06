package com.misakamikoto.springboot.api.admin.mapper;

import com.misakamikoto.springboot.api.admin.dto.Admin;
import com.misakamikoto.springboot.api.config.database.mapper.MasterMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MasterMapper
public interface AdminMasterMapper {
    List<Admin> getExample();
}
