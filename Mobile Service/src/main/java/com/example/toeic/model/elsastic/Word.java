package com.example.toeic.model.elsastic;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "toeic", type = "word")
@Data
public class Word {

    @Id
    private Long id;

    private String word;
    private String uk;
    private String us;
}
