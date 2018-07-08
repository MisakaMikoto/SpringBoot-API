package com.misakamikoto.springboot.api.book.history.repository;

import com.misakamikoto.springboot.api.book.history.dto.BookHistory;
import org.springframework.data.repository.CrudRepository;

public interface BookHistoryRepository extends CrudRepository<BookHistory, Long> {
}
