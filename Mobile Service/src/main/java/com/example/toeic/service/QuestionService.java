package com.example.toeic.service;

import com.example.toeic.model.exam.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Pageable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface QuestionService {
    List<Question> findAll();
    List<Question> findByPart(Integer partId);
    List<Question> findByPartAndExams(Integer partId, Integer examId);
    void  prepare() throws IOException;
}
