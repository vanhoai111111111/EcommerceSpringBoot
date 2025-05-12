package com.example.backend.Service;


import com.example.backend.dto.WishListDTO;
import com.example.backend.entity.Product;
import com.example.backend.entity.User;
import com.example.backend.entity.WishList;
import com.example.backend.Repository.ProductRepository;
import com.example.backend.Repository.UserRepository;
import com.example.backend.Repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListService {

    @Autowired
    private WishListRepository wishListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public WishListDTO addToWishList(WishListDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check duplicate
        wishListRepository.findByUserIdAndProductId(dto.getUserId(), dto.getProductId())
                .ifPresent(w -> { throw new RuntimeException("Product already in wishlist"); });

        WishList wishList = new WishList();
        wishList.setUser(user);
        wishList.setProduct(product);

        WishList saved = wishListRepository.save(wishList);

        dto.setId(saved.getId());
        return dto;
    }

    public List<WishListDTO> getWishlistByUser(Long userId) {
        return wishListRepository.findByUserId(userId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public void removeFromWishList(Long id) {
        wishListRepository.deleteById(id);
    }

    private WishListDTO mapToDTO(WishList wishList) {
        WishListDTO dto = new WishListDTO();
        dto.setId(wishList.getId());
        dto.setProductId(wishList.getProduct().getId());
        dto.setUserId(wishList.getUser().getId());
        return dto;
    }
}
