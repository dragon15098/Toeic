package com.example.toeic.repository;

import com.example.toeic.model.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.example.toeic.model.exam.GroupQuestion;

import java.util.List;

public interface GroupQuestionRepository extends JpaRepository<GroupQuestion, Integer>, JpaSpecificationExecutor<GroupQuestion> {
    List<GroupQuestion> findAllByPartIdAndExamId(Integer partId, Integer examId);
}