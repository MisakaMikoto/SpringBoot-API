package com.misakamikoto.springboot.api.services.book.service;

import com.misakamikoto.springboot.api.services.book.dto.Bookmark;
import com.misakamikoto.springboot.api.services.book.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookmarkService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public List<Bookmark> getListById(String memberId) {
        return StreamSupport.stream(this.bookmarkRepository.findAll().spliterator(), false)
                .filter(searchHistory -> memberId.equals(searchHistory.getMemberId())).collect(Collectors.toList());
    }

    public Bookmark save(Bookmark bookMark) {
        return this.bookmarkRepository.save(bookMark);
    }
}
