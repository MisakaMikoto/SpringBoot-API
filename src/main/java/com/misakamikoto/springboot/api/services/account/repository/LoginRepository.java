package com.misakamikoto.springboot.api.services.account.repository;

import com.misakamikoto.springboot.api.services.account.dto.Member;
import org.springframework.data.repository.CrudRepository;

public interface LoginRepository extends CrudRepository<Member, String> {
}
