package com.example.toeic.controller;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.Question;
import com.example.toeic.service.ExamService;
import org.elasticsearch.common.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/exam")
public class ExamController {

    @Autowired
    ExamService examService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Exam>> findByPart() {
        return ResponseEntity.ok(examService.findAll());
    }

}
