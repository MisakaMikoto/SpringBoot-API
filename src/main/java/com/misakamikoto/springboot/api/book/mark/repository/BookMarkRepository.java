package com.misakamikoto.springboot.api.book.mark.repository;

import com.misakamikoto.springboot.api.book.mark.dto.BookMark;
import org.springframework.data.repository.CrudRepository;

public interface BookMarkRepository extends CrudRepository<BookMark, Long> {
}
