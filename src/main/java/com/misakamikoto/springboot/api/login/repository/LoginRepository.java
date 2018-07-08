package com.misakamikoto.springboot.api.login.repository;

import com.misakamikoto.springboot.api.login.dto.Member;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Member, String> {
}
