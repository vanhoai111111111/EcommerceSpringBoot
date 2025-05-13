package com.example.ecommercespringboot.service.impl;

import com.example.ecommercespringboot.dto.WishlistDto;
import com.example.ecommercespringboot.entity.Product;
import com.example.ecommercespringboot.entity.User;
import com.example.ecommercespringboot.entity.WishList;
import com.example.ecommercespringboot.repository.ProductRepository;
import com.example.ecommercespringboot.repository.UserRepository;
import com.example.ecommercespringboot.repository.WishlistRepository;
import com.example.ecommercespringboot.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final WishlistRepository wishlistRepository;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto) {
        Optional<Product> optionalProduct = productRepository.findById(wishlistDto.getProductId());
        Optional<User> optionalUser = userRepository.findById(wishlistDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()) {
            WishList wishlist = new WishList();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepository.save(wishlist).getWishlistDto();
        }
        return null;
    }

    public List<WishlistDto> getWishlistsByUserId(Long userId) {
        return wishlistRepository.findAllByUserId(userId).stream().map(WishList::getWishlistDto).collect(Collectors.toList());
    }

}
