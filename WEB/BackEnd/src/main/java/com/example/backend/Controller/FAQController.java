package com.example.backend.Controller;

import com.example.backend.dto.FAQDTO;
import com.example.backend.Service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faqs")
public class FAQController {

    @Autowired
    private FAQService faqService;

    @PostMapping
    public FAQDTO createFAQ(@RequestBody FAQDTO dto) {
        return faqService.createFAQ(dto);
    }

    @GetMapping("/product/{productId}")
    public List<FAQDTO> getFAQsByProduct(@PathVariable Long productId) {
        return faqService.getFAQsByProduct(productId);
    }

    @PutMapping("/{id}")
    public FAQDTO updateFAQ(@PathVariable Long id, @RequestBody FAQDTO dto) {
        return faqService.updateFAQ(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteFAQ(@PathVariable Long id) {
        faqService.deleteFAQ(id);
    }
}
