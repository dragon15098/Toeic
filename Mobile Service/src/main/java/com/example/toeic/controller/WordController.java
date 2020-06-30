package com.example.toeic.controller;

import com.example.toeic.model.elsastic.Word;
import com.example.toeic.model.exam.Question;
import com.example.toeic.repository.elasticsearch.WordSearchRepository;
import com.example.toeic.service.WordSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/word")
public class WordController {

    @Autowired
    WordSearchService wordSearchService;

    @GetMapping("/getSuggestWord/{word}")
    public ResponseEntity<List<Word>> getSuggestWord(@PathVariable("word") String word,
                                                     @RequestParam("pageNumber") Integer pageNumber,
                                                     @RequestParam("pageSize") Integer pageSize
    ) {
        return ResponseEntity.ok(wordSearchService.findByWord(word, PageRequest.of(pageNumber, pageSize)));
    }

    @GetMapping("/prepare")
    public void prepare() throws IOException {
        wordSearchService.save();
    }

    @GetMapping("/delete/ALl")
    public void deleteAll() throws IOException {
        wordSearchService.deleteAll();
    }


}
