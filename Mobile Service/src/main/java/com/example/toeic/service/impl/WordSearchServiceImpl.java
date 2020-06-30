package com.example.toeic.service.impl;

import com.example.toeic.model.elsastic.Word;
import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.Part;
import com.example.toeic.repository.GroupQuestionRepository;
import com.example.toeic.repository.elasticsearch.WordSearchRepository;
import com.example.toeic.service.WordSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WordSearchServiceImpl implements WordSearchService {
    private final WordSearchRepository wordSearchRepository;

    @Override
    public List<Word> findAllByWord(String word) {
        return wordSearchRepository.findAllByWord(word);
    }

    @Override
    public List<Word> findByWord(String word, PageRequest pageRequest) {
        return wordSearchRepository.findAllByWordLike(word, pageRequest);
    }

    @Override
    public void save() throws IOException {

        FileInputStream fis = new FileInputStream("H:/Work/Toeic/Service/word/word.txt");
        String s = IOUtils.toString(fis, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, Word> hashMap = objectMapper.readValue(s, HashMap.class);
        for (String key : hashMap.keySet()) {
            wordSearchRepository.save(objectMapper.convertValue(hashMap.get(key), Word.class));
        }
        System.out.println("AAAAA");
    }

    @Override
    public void deleteAll() {
        wordSearchRepository.deleteAll();
    }
}
