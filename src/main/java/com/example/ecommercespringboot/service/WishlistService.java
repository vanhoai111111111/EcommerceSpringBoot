package com.example.ecommercespringboot.service;

import com.example.ecommercespringboot.dto.WishlistDto;

import java.util.List;

public interface WishlistService {

    WishlistDto addProductToWishlist(WishlistDto wishlistDto);

    List<WishlistDto> getWishlistsByUserId(Long userId);

}
