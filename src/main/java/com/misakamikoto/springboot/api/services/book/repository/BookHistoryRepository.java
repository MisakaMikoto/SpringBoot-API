package com.misakamikoto.springboot.api.services.book.repository;

import com.misakamikoto.springboot.api.services.book.dto.History;
import org.springframework.data.repository.CrudRepository;

public interface BookHistoryRepository extends CrudRepository<History, Long> {
}
