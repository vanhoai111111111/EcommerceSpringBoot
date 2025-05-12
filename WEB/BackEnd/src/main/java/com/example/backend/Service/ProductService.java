package com.example.backend.Service;

import com.example.backend.dto.ProductDto;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.Repository.CategoryRepository;
import com.example.backend.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDto createProduct(String name, Long price, String description, Long categoryId, MultipartFile file) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(category);

        try {
            product.setImg(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image", e);
        }

        product = productRepository.save(product);
        return product.getDto();
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product.getDto();
    }
}
