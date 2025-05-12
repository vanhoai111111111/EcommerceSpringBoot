package com.example.backend.Controller;

import com.example.backend.Repository.CategoryRepository;
import com.example.backend.Repository.ProductRepository;
import com.example.backend.dto.ProductDto;
import com.example.backend.Service.ProductService;
import com.example.backend.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam String name,
            @RequestParam Long price,
            @RequestParam String description,
            @RequestParam Long categoryId,
            @RequestParam("img") MultipartFile file) {

        ProductDto dto = productService.createProduct(name, price, description, categoryId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        ProductDto product = productService.getProductById(id);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Hoặc IMAGE_PNG tùy loại ảnh
                .body(product.getByteImg());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam Long price,
            @RequestParam String description,
            @RequestParam Long categoryId,
            @RequestParam(required = false) MultipartFile img) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        Optional<com.example.backend.entity.Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()) {
            return ResponseEntity.badRequest().body("Category not found");
        }

        Product product = optionalProduct.get();
        product.setName(name);
        product.setPrice(price);
        product.setDescription(description);
        product.setCategory(optionalCategory.get());

        try {
            if (img != null && !img.isEmpty()) {
                product.setImg(img.getBytes());
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error reading image file");
        }

        productRepository.save(product);
        return ResponseEntity.ok("Product updated successfully");
    }

    //  Delete Product
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.badRequest().body("Product not found");
        }

        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
