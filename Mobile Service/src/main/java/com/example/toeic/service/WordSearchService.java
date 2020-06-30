package com.example.toeic.service;

import com.example.toeic.model.elsastic.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

public interface WordSearchService {
    List<Word> findAllByWord(String word);
    List<Word> findByWord(String word, PageRequest page);
    void save() throws IOException;
    void deleteAll();
}
