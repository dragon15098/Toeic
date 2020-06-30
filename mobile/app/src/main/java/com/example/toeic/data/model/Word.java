package com.example.toeic.data.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Word {
    private Long id;
    private String word;
    private String uk;
    private String us;
    private String ukMp3;
    private String usMp3;
    private String description;
}
