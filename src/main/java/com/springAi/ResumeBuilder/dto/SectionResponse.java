package com.springAi.ResumeBuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponse {

    private String title;
    private String content;
}
