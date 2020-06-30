package com.example.toeic.repository.elasticsearch;

import com.example.toeic.model.elsastic.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface WordSearchRepository extends ElasticsearchRepository<Word, Long> {
    List<Word> findAllByWord(String word);
    List<Word> findAllByWordLike(String word, Pageable pageable);
}
