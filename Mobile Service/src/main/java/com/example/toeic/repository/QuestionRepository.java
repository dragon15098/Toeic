package com.example.toeic.repository;

import com.example.toeic.model.exam.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Integer>, JpaSpecificationExecutor<Question> {
    List<Question> findAllByPartId(Integer partId);
    List<Question> findAllByPartIdAndExamId(Integer partId, Integer examId);
    Page<Question> findAllByPartId(Integer partId, Pageable pageable);
}
