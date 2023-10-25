package com.example.WebDT.controller;

import com.example.WebDT.entity.Category;
import com.example.WebDT.entity.Product;
import com.example.WebDT.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/category")

public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "admin/category/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category",new Category());
        return "admin/category/create";
    }
    @PostMapping("/create")
    public String create(@Valid Category newCategory,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            return "admin/category/create";
        }
        categoryService.saveCategory(newCategory);
        return "redirect:/admin/category";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Category category = categoryService.getCategoryById(id);
        if (category == null){
            return "not-found";
        }
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, @Valid @ModelAttribute("category") Category updatedCategory, BindingResult result, Model model){
        if (result.hasErrors()){
            return "admin/category/edit";
        }
        Category category = categoryService.getCategoryById(id);
        if (category == null){
            return "error";
        }
        category.setName(updatedCategory.getName());
        categoryService.saveCategory(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/category";
    }
}
