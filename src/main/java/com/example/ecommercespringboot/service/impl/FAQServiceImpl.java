package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.dto.FAQDto;
import com.example.ecommercespringboot.entity.FAQ;
import com.example.ecommercespringboot.entity.Product;
import com.example.ecommercespringboot.repository.FAQRepository;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.service.FAQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService {

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;

    public FAQDto postFAQ(Long productId, FAQDto faqDto) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()) {
            FAQ faq = new FAQ();
            faq.setQuestion(faqDto.getQuestion());
            faq.setAnswer(faqDto.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDto();
        }
        return null;
    }

}
