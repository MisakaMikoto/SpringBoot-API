package com.misakamikoto.springboot.api.services.book.service;

import com.misakamikoto.springboot.api.services.book.dto.Bookmark;
import com.misakamikoto.springboot.api.services.book.dto.History;
import com.misakamikoto.springboot.api.services.book.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookmarkService {

    @Autowired
    BookmarkRepository bookmarkRepository;

    public List<Bookmark> getListOrderId(String memberId) {
        return StreamSupport.stream(this.bookmarkRepository.findAll().spliterator(), false)
                .filter(searchHistory -> memberId.equals(searchHistory.getMemberId())).collect(Collectors.toList());
    }

    public List<Bookmark> getListOrderTitle(String memberId) {
        List<Bookmark> bookmarks = this.getListOrderId(memberId);
        bookmarks.sort(Comparator.comparing(Bookmark::getTitle));
        return bookmarks;
    }

    public List<Bookmark> getListOrderStatus(String memberId) {
        List<Bookmark> bookmarks = this.getListOrderId(memberId);
        bookmarks.sort(Comparator.comparing(Bookmark::getStatus));
        return bookmarks;
    }

    public List<Bookmark> getListOrderSalePrice(String memberId) {
        List<Bookmark> bookmarks = this.getListOrderId(memberId);
        bookmarks.sort(Comparator.comparing(Bookmark::getSalePrice));
        return bookmarks;
    }

    public List<Bookmark> getListByIsbn(String memberId, String isbn) {
        List<Bookmark> bookmarks = this.getListOrderId(memberId);
        return StreamSupport.stream(bookmarks.spliterator(), false)
                .filter(searchBookmark -> isbn.equals(searchBookmark.getIsbn())).collect(Collectors.toList());
    }

    public Bookmark save(Bookmark bookmark) {
        return this.bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> deleteBookmarks(String memberId, String historyIds) {
        for(String id : historyIds.split(",")) {
            this.bookmarkRepository.deleteById(Long.valueOf(id));
        }
        return this.getListOrderId(memberId);
    }
}
