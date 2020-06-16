package com.example.toeic.controller;

import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Part;
import com.example.toeic.model.exam.Question;
import com.example.toeic.service.GroupQuestionService;
import com.example.toeic.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/group_question")
public class GroupQuestionController {

    @Autowired
    GroupQuestionService groupQuestionService;

    @GetMapping("/findPartThreeByExamId/{id}")
    public ResponseEntity<List<GroupQuestion>> findQuestionPartThreeByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(groupQuestionService.findByPartAndExams(Part.PartIndex.PART_THREE.getValue(), examId));
    }

    @GetMapping("/findPartFourByExamId/{id}")
    public ResponseEntity<List<GroupQuestion>> findQuestionPartFourByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(groupQuestionService.findByPartAndExams(Part.PartIndex.PART_FOUR.getValue(), examId));
    }

    @GetMapping("/findPartSixByExamId/{id}")
    public ResponseEntity<List<GroupQuestion>> findQuestionPartSixByExamId(@PathVariable("id") Integer examId) {
        return ResponseEntity.ok(groupQuestionService.findByPartAndExams(Part.PartIndex.PART_SIX.getValue(), examId));
    }
}
