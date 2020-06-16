package com.example.toeic.repository.elasticsearch;

import com.example.toeic.model.elsastic.Word;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WordSearchRepository extends ElasticsearchRepository<Word, Long> {

}
