package com.example.WebDT.services;

import com.example.WebDT.entity.Category;
import com.example.WebDT.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;
    public List<Category> getAllCategories(){
        return (List<Category>) repo.findAll();
    }

    public void deleteCategory(int id){
        repo.deleteById(id);
    }

    public Category getCategoryById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Category saveCategory(Category c) {
        return repo.save(c);
    }
}
