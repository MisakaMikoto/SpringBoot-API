package com.misakamikoto.springboot.api.biz.mapper;

import com.misakamikoto.springboot.api.biz.dto.Biz;
import com.misakamikoto.springboot.api.config.database.mapper.MasterMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@MasterMapper
public interface BizMapper {
    List<Biz> getExample();
}
