package com.example.WebDT.controller;

import com.example.WebDT.entity.Category;
import com.example.WebDT.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("listCategory", categoryService.getAllCategories());
        return "category/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("category",new Category());
        return "category/create";
    }
    @PostMapping("/create")
    public String create(@Valid Category newCategory,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            return "category/create";
        }
        categoryService.saveCategory(newCategory);
        return "redirect:/category/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Category category = categoryService.getCategoryById(id);
        if (category == null){
            return "not-found";
        }
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id, @Valid @ModelAttribute("category") Category updatedCategory, BindingResult result, Model model){
        if (result.hasErrors()){
            return "category/edit";
        }
        Category category = categoryService.getCategoryById(id);
        if (category == null){
            return "error";
        }
        category.setName(updatedCategory.getName());
        categoryService.saveCategory(category);
        return "redirect:/category";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id){
        categoryService.deleteCategory(id);
        return "redirect:/category";
    }

}
