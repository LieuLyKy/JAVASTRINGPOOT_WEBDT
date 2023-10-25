package com.example.WebDT.services;

import com.example.WebDT.entity.Product;
import com.example.WebDT.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;

    public void add(Product c){
        repo.save(c);
    }
    public List<Product> getAll(){
        return (List<Product>) repo.findAll();
    }

    public Page<Product> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,9);
        return repo.findAll(pageable);
    }


    public Page<Product> getAllBook(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public void deleteProduct(int id){
        repo.deleteById(id);
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public void updateProduct(Product b) {
        repo.save(b);
    }

//    public List<Product> getOtherProducts(int currentProductId) {
//        List<Product> otherProducts = getAll();  // Sử dụng phương thức getAll để lấy tất cả sản phẩm
//        otherProducts.removeIf(product -> product.getId() == currentProductId);
//        return otherProducts;
//    }

    public List<Product> getRelatedProducts(int currentProductId) {
        Product currentProduct = getProductById(currentProductId);
        List<Product> allProducts = getAll();
        List<Product> relatedProducts = new ArrayList<>();

        for (Product product : allProducts) {
            if (product.getId() != currentProductId && product.getCategory().equals(currentProduct.getCategory())) {
                relatedProducts.add(product);
            }
        }

        return relatedProducts;
    }

    public List<Product> getProductsByCategoryId(int categoryId) {
        return repo.findByCategoryId(categoryId);
    }

//    public List<Product> getRelatedProducts(int currentProductId) {
//        Product currentProduct = getProductById(currentProductId);
//        List<Product> allProducts = getAll();
//        List<Product> relatedProducts = new ArrayList<>();
//
//        for (Product product : allProducts) {
//            if (product.getId() != currentProductId && product.getCategory().equals(currentProduct.getCategory())) {
//                relatedProducts.add(product);
//            }
//        }
//
//        return relatedProducts;
//    }

//    public List<Product> getOtherProducts(int currentProductId) {
//        Product currentProduct = getProductById(currentProductId);
//        List<Product> allProducts = getAll();
//        List<Product> otherProducts = new ArrayList<>();
//
//        for (Product product : allProducts) {
//            if (product.getId() != currentProductId) {
//                otherProducts.add(product);
//            }
//        }
//
//        return otherProducts;
//    }
}
