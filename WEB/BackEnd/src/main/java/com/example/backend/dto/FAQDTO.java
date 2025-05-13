package com.example.backend.dto;

import lombok.Data;

@Data
public class FAQDTO {
    private Long id;
    private String question;
    private String answer;
    private Long productId;
}
