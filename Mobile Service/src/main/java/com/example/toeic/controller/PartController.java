package com.example.toeic.controller;

import com.example.toeic.model.exam.Question;
import com.example.toeic.service.PartService;
import com.example.toeic.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/part")
public class PartController {

    @Autowired
    QuestionService partService;
}
