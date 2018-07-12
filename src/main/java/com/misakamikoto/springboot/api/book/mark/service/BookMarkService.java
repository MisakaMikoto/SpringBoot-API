package com.misakamikoto.springboot.api.book.mark.service;

import com.misakamikoto.springboot.api.book.mark.dto.BookMark;
import com.misakamikoto.springboot.api.book.mark.repository.BookMarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookMarkService {

    @Autowired
    BookMarkRepository bookMarkRepository;

    public List<BookMark> getListById(String memberId) {
        return StreamSupport.stream(this.bookMarkRepository.findAll().spliterator(), false)
                .filter(searchHistory -> memberId.equals(searchHistory.getMemberId())).collect(Collectors.toList());
    }

    public BookMark save(BookMark bookMark) {
        return this.bookMarkRepository.save(bookMark);
    }
}
