package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.FAQDto;

public interface FAQService {

    FAQDto postFAQ(Long productId, FAQDto faqDto);

}
