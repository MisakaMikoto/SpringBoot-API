package com.misakamikoto.springboot.api.book.history.repository;

import com.misakamikoto.springboot.api.book.history.dto.SearchHistory;
import org.springframework.data.repository.CrudRepository;

public interface SearchHistoryRepository extends CrudRepository<SearchHistory, Long> {
}
