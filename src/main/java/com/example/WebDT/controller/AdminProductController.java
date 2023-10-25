package com.example.WebDT.controller;

import jakarta.validation.Valid;
import com.example.WebDT.entity.Product;
import com.example.WebDT.repository.ProductRepository;
import com.example.WebDT.services.CategoryService;
import com.example.WebDT.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
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
import java.util.UUID;

@RequestMapping("admin/products")
@Controller
@Secured("ADMIN")

public class AdminProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{pageNumber}")
    public String getOnPage(Model model, @PathVariable("pageNumber") int currentPage){
        Page<Product> page = productService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Product> products = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("listProduct", products);

        return "admin/products/index";
    }
    @GetMapping("")
    public String getAllPages(Model model){
        return getOnPage(model, 1);
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("product",new Product());
        model.addAttribute("listCategory",categoryService.getAllCategories());
        return "admin/products/create";
    }

    @PostMapping("/create")
    public String create(@Valid Product newProduct,
                         @RequestParam MultipartFile imageProduct,
                         BindingResult result,
                         Model model){
        if (result.hasErrors()){
            model.addAttribute("listCategory",categoryService.getAllCategories());
            return "admin/products/create";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFile = UUID.randomUUID() +  ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                newProduct.setImage(newImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        productService.add(newProduct);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model){
        Product product = productService.getProductById(id);
        if (product == null){
            return "not-found";
        }
        model.addAttribute("listCategory",categoryService.getAllCategories());
        model.addAttribute("product", product);
        return "/admin/products/edit";
    }

    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") int id,
                             @RequestParam MultipartFile imageProduct,
                             @Valid @ModelAttribute("product") Product updatedProduct,
                             BindingResult result,
                             Model model){
        if (result.hasErrors()){
            model.addAttribute("listCategory",categoryService.getAllCategories());
            return "admin/products/edit";
        }

        Product product = productService.getProductById(id);
        if (product == null){
            return "error";
        }
        if(imageProduct != null && imageProduct.getSize() > 0)
        {
            try {
                File saveFile = new ClassPathResource("static/images").getFile();
                String newImageFile = UUID.randomUUID() +  ".png";
                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + newImageFile);
                Files.copy(imageProduct.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                product.setImage(newImageFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setDescription(updatedProduct.getDescription());
        productService.updateProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

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

        return "admin/products/index";
    }



}
