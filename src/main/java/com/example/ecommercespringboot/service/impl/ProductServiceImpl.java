package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.dto.ProductDto;
import com.example.ecommercespringboot.entity.Category;
import com.example.ecommercespringboot.entity.Product;
import com.example.ecommercespringboot.repository.CategoryRepository;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private byte[] getDefaultImage() throws IOException {
        // Lấy ảnh mặc định từ thư mục resources
        Path defaultImagePath = Paths.get("src/main/resources/static/images/default-image.jpg");
        return Files.readAllBytes(defaultImagePath);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) throws IOException {
        // Tạo đối tượng Product mới
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        // Kiểm tra nếu có ảnh được tải lên, nếu không thì sử dụng ảnh mặc định
        byte[] imageBytes = (productDto.getImg() != null && !productDto.getImg().isEmpty())
                ? productDto.getImg().getBytes()
                : getDefaultImage();

        // Gán ảnh cho sản phẩm
        product.setImg(imageBytes);

        // Tìm kiếm danh mục sản phẩm
        Category category = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Gán danh mục cho sản phẩm
        product.setCategory(category);

        // Lưu sản phẩm vào cơ sở dữ liệu và trả về DTO của sản phẩm vừa thêm
        Product savedProduct = productRepository.save(product);
        return savedProduct.getDto();
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getAllProductByName(String name) {
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.map(Product::getDto).orElse(null);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        if (optionalProduct.isPresent() && optionalCategory.isPresent()) {
            Product product = optionalProduct.get();
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(optionalCategory.get());

            // Cập nhật ảnh nếu có ảnh mới được tải lên
            if (productDto.getImg() != null && !productDto.getImg().isEmpty()) {
                product.setImg(productDto.getImg().getBytes());
            }

            Product updatedProduct = productRepository.save(product);
            return updatedProduct.getDto();
        } else {
            return null; // Không tìm thấy sản phẩm hoặc danh mục
        }
    }
}
