package com.example.WebDT.repository;

import  com.example.WebDT.entity.Category;
import com.example.WebDT.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    @Query("SELECT p FROM Category p WHERE p.name like  %:key%")
//    Page<Category> searchCategories(@Param("key") String key, Pageable pageable);
}
