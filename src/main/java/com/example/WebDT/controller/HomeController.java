package com.example.WebDT.controller;

import com.example.WebDT.entity.Category;
import com.example.WebDT.entity.Product;
import com.example.WebDT.repository.ProductRepository;
import com.example.WebDT.services.CategoryService;
import com.example.WebDT.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Controller
@Secured("")
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/search")
    public String searchPage (@RequestParam("keyword") String keyword, Model model){
        List<Product> result = new ArrayList<>();
        Page<Product> page = productService.findPage(1);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();

        productService.getAll().forEach(c -> {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase()) || c.getCategory().getName().toLowerCase().contains(keyword.toLowerCase()))
                result.add(c);
        });

        model.addAttribute("currentPage", 1);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listProduct", result);

        return "home";
    }
    @GetMapping("/{pageNumber}")
    public String getOnPage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Product> page = productService.findPage(currentPage);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listProduct", products);

        return "home";
    }
    @GetMapping("")
    public String getAllPages(Model model){
//        List<Category> categories = categoryService.getAllCategories();
//        model.addAttribute("categories", categories);
        return getOnPage(model, 1);
    }

    @GetMapping("/category/{categoryId}")
    public String getProductByCategory(@PathVariable("categoryId") int categoryId, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        model.addAttribute("listProduct", products);
        return "home";
    }
    @GetMapping("/Blog")
    public String contact(){
        return "Blog";
    }

    @GetMapping("/encode/{pass}")
    @ResponseBody
    public String encode(@PathVariable String pass)
    {
        return new BCryptPasswordEncoder().encode(pass);
    }
}
