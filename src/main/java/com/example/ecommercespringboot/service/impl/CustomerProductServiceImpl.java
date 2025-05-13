package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.dto.ProductDetailDto;
import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.entity.FAQ;
import com.example.ecommercespringboot.entity.Product;
import com.example.ecommercespringboot.entity.Review;
import com.example.ecommercespringboot.repository.FAQRepository;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.repository.ReviewRepository;
import com.example.ecommercespringboot.service.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductByTitle(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailDto getProductDetailById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewList = reviewRepository.findAllByProductId(productId);

            ProductDetailDto productDetailDto = new ProductDetailDto();

            productDetailDto.setProductDto(optionalProduct.get().getDto());
            productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
            return productDetailDto;
        }
        return null;
    }

}
