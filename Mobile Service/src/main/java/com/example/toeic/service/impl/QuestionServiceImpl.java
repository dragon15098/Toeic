package com.example.toeic.service.impl;

import com.example.toeic.model.exam.Exam;
import com.example.toeic.model.exam.GroupQuestion;
import com.example.toeic.model.exam.Part;
import com.example.toeic.model.exam.Question;
import com.example.toeic.repository.*;
import com.example.toeic.service.QuestionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    private final ExamRepository examRepository;
    private final AnswerRepository answerRepository;
    private final GroupQuestionRepository groupQuestionRepository;


    private final PartRepository partRepository;

    @Override
    public List<Question> findAll() {
        return StreamSupport.stream(questionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Question> findByPart(Integer partId) {
        return questionRepository.findAllByPartId(partId);
    }

    @Override
    public List<Question> findByPartAndExams(Integer partId, Integer examId) {
        return questionRepository.findAllByPartIdAndExamId(partId, examId);
    }

    @Override
    public void prepare() throws IOException {

        FileInputStream fis = new FileInputStream("H:/Work/Toeic/Service/exam1.txt");
        String s = IOUtils.toString(fis, StandardCharsets.UTF_8);

        ObjectMapper objectMapper = new ObjectMapper();
        Exam exam = objectMapper.readValue(s, Exam.class);

        Exam exam1 = new Exam();
        exam1.setName("Đề 1");
        examRepository.save(exam1);
        Part partOne = exam.parts.get(0);
        partOne.questions.forEach(question -> {
            question.setExamId(exam1.getId());
            question.setPartId(1);
            question.answers.forEach(answer -> {
                answer.setQuestion(question);
//                answerRepository.save(answer);
            });
            questionRepository.save(question);
        });

        Part partTwo = exam.parts.get(1);
        partTwo.questions.forEach(question -> {
            question.setExamId(exam1.getId());
            question.setPartId(2);
            question.answers.forEach(answer -> {
                answer.setQuestion(question);
//                answerRepository.save(answer);
            });
            questionRepository.save(question);
        });


        Part partThree = exam.parts.get(2);
        partThree.groupQuestions.forEach(groupQuestion -> {
            groupQuestion.setExamId(exam1.getId());
            groupQuestion.setPartId(3);

            groupQuestion.getQuestions().forEach(question -> {
                question.setExamId(exam1.getId());
                question.setPartId(3);
                question.setGroupQuestion(groupQuestion);
//                GroupQuestion groupQuestion1 = new GroupQuestion();
//                groupQuestion1.setId(groupQuestion.getId());
//                question.setGroupQuestion(groupQuestion1);
//                questionRepository.save(question);
                question.answers.forEach(answer -> {
                    answer.setQuestion(question);
//                    answerRepository.save(answer);
                });
            });
            groupQuestionRepository.save(groupQuestion);
        });

        Part partFour = exam.parts.get(3);
        partFour.groupQuestions.forEach(groupQuestion -> {
            groupQuestion.setExamId(exam1.getId());
            groupQuestion.setPartId(4);
            groupQuestion.getQuestions().forEach(question -> {
                question.setExamId(exam1.getId());
                question.setPartId(4);
                question.setGroupQuestion(groupQuestion);
//                GroupQuestion groupQuestion1 = new GroupQuestion();
//                groupQuestion1.setId(groupQuestion.getId());
//                question.setGroupQuestion(groupQuestion1);
//                questionRepository.save(question);
                question.answers.forEach(answer -> {
                    answer.setQuestion(question);
//                    answerRepository.save(answer);
                });
            });
            groupQuestionRepository.save(groupQuestion);
        });

        Part partFive = exam.parts.get(4);
        partFive.questions.forEach(question -> {
            question.setExamId(exam1.getId());
            question.setPartId(5);

            question.answers.forEach(answer -> {
                answer.setQuestion(question);
//                answerRepository.save(answer);
            });
            questionRepository.save(question);
        });


        Part partSix = exam.parts.get(5);
        partSix.groupQuestions.forEach(groupQuestion -> {
            groupQuestion.setExamId(exam1.getId());
            groupQuestion.setPartId(6);
            groupQuestion.getQuestions().forEach(question -> {
                question.setExamId(exam1.getId());
                question.setPartId(6);
                question.setGroupQuestion(groupQuestion);
//                GroupQuestion groupQuestion1 = new GroupQuestion();
//                groupQuestion1.setId(groupQuestion.getId());
//                question.setGroupQuestion(groupQuestion1);
//                questionRepository.save(question);
                question.answers.forEach(answer -> {
                    answer.setQuestion(question);
//                    answerRepository.save(answer);
                });

            });
            groupQuestionRepository.save(groupQuestion);
        });


        System.out.println("AAAAA");
    }
}
