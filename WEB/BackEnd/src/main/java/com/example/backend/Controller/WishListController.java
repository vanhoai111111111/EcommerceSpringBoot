package com.example.backend.Controller;



import com.example.backend.dto.WishListDTO;
import com.example.backend.Service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @PostMapping
    public WishListDTO addToWishlist(@RequestBody WishListDTO dto) {
        return wishListService.addToWishList(dto);
    }

    @GetMapping("/user/{userId}")
    public List<WishListDTO> getWishlistByUser(@PathVariable Long userId) {
        return wishListService.getWishlistByUser(userId);
    }

    @DeleteMapping("/{id}")
    public void removeFromWishlist(@PathVariable Long id) {
        wishListService.removeFromWishList(id);
    }
}

