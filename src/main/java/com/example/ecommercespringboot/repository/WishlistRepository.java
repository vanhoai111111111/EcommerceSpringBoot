package com.example.ecommercespringboot.repository;

import com.example.ecommercespringboot.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<WishList, Long> {

    List<WishList> findAllByUserId(Long userId);

}
