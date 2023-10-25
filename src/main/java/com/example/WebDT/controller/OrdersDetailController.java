package com.example.WebDT.controller;

import com.example.WebDT.entity.OrdersDetail;
import com.example.WebDT.services.OrdersDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/admin/ordersdetails")
public class OrdersDetailController {


    @GetMapping
    public List<OrdersDetail> getAllOrdersDetails() {
        return ordersDetailService.getAllOrdersDetails();
    }


    private final OrdersDetailService ordersDetailService;

    public OrdersDetailController(OrdersDetailService ordersDetailService) {
        this.ordersDetailService = ordersDetailService;
    }

    @GetMapping("/revenue")
    public String showTotalRevenue(Model model) {
        long totalRevenue = ordersDetailService.calculateTotalRevenue();
        model.addAttribute("totalRevenue", totalRevenue);
        return "admin/ordersdetails/revenue";
    }

    @GetMapping("/quantity")
    public String showTotalQuantitySold(Model model) {
        int totalQuantitySold = ordersDetailService.calculateTotalQuantitySold();
        model.addAttribute("totalQuantitySold", totalQuantitySold);
        return "admin/ordersdetails/quantity";
    }

//    @GetMapping("/product/{productId}/quantity")
//    public String showQuantitySoldByProductId(@PathVariable Integer productId, Model model) {
//        int quantitySold = ordersDetailService.calculateQuantitySoldByProductId(productId);
//        model.addAttribute("quantitySold", quantitySold);
//        return "admin/ordersdetails/productQuantity";
//    }

    @GetMapping("/product/{productId}/quantity")
    public String showQuantitySoldByProductId(@PathVariable Integer orderId, Model model) {
        int quantitySold = ordersDetailService.calculateQuantitySoldByProductId(orderId);
        List<OrdersDetail> ordersDetails = ordersDetailService.getOrdersDetailsByProductId(orderId);
        model.addAttribute("productId", orderId);
        model.addAttribute("quantitySold", quantitySold);
        model.addAttribute("ordersDetails", ordersDetails);
        return "admin/ordersdetails/productQuantity";
    }
}
