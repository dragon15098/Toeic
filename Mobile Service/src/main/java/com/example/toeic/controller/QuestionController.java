package com.example.toeic.controller;

import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Part;
import com.example.toeic.model.exam.Question;
import com.example.toeic.service.GroupQuestionService;
import com.example.toeic.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;


    @GetMapping("/all")
    public ResponseEntity<List<Question>> findAll() {
        return ResponseEntity.ok(questionService.findAll());
    }

    @GetMapping("/findByPart/{id}")
    public ResponseEntity<List<Question>> findByPart(@PathVariable("id") Integer pathId) {
        return ResponseEntity.ok(questionService.findByPart(pathId));
    }

    @GetMapping("/findPartOneByExamId/{id}")
    public ResponseEntity<List<Question>> findQuestionPartOneByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(questionService.findByPartAndExams(Part.PartIndex.PART_ONE.getValue(), examId));
    }

    @GetMapping("/findPartTwoByExamId/{id}")
    public ResponseEntity<List<Question>> findQuestionPartTwoByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(questionService.findByPartAndExams(Part.PartIndex.PART_TWO.getValue(), examId));
    }

    @GetMapping("/findPartFiveByExamId/{id}")
    public ResponseEntity<List<Question>> findQuestionPartFiveByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(questionService.findByPartAndExams(Part.PartIndex.PART_FIVE.getValue(), examId));
    }

    @GetMapping("/prepare")
    public void prepare() throws IOException {
        questionService.prepare();
    }

}
