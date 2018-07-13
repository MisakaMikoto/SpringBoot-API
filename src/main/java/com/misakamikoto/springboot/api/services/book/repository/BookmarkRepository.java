package com.misakamikoto.springboot.api.services.book.repository;

import com.misakamikoto.springboot.api.services.book.dto.Bookmark;
import org.springframework.data.repository.CrudRepository;

public interface BookmarkRepository extends CrudRepository<Bookmark, Long> {
}
