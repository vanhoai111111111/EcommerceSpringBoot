package com.example.backend.Service;

import com.example.backend.dto.FAQDTO;
import com.example.backend.entity.FAQ;
import com.example.backend.entity.Product;
import com.example.backend.Repository.FAQRepository;
import com.example.backend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FAQService {

    @Autowired
    private FAQRepository faqRepository;

    @Autowired
    private ProductRepository productRepository;

    public FAQDTO createFAQ(FAQDTO dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        FAQ faq = new FAQ();
        faq.setQuestion(dto.getQuestion());
        faq.setAnswer(dto.getAnswer());
        faq.setProduct(product);

        FAQ saved = faqRepository.save(faq);
        dto.setId(saved.getId());
        return dto;
    }

    public List<FAQDTO> getFAQsByProduct(Long productId) {
        return faqRepository.findByProductId(productId).stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public FAQDTO updateFAQ(Long id, FAQDTO dto) {
        FAQ faq = faqRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FAQ not found"));

        faq.setQuestion(dto.getQuestion());
        faq.setAnswer(dto.getAnswer());
        FAQ updated = faqRepository.save(faq);

        return mapToDTO(updated);
    }

    public void deleteFAQ(Long id) {
        faqRepository.deleteById(id);
    }

    private FAQDTO mapToDTO(FAQ faq) {
        FAQDTO dto = new FAQDTO();
        dto.setId(faq.getId());
        dto.setQuestion(faq.getQuestion());
        dto.setAnswer(faq.getAnswer());
        dto.setProductId(faq.getProduct().getId());
        return dto;
    }
}
