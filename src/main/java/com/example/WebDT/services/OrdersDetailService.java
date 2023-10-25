package com.example.WebDT.services;

import com.example.WebDT.entity.OrdersDetail;
import com.example.WebDT.repository.OrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersDetailService {
//    private final OrderDetailRepository ordersDetailRepository;
//
//    public OrdersDetailService(OrderDetailRepository ordersDetailRepository) {
//        this.ordersDetailRepository = ordersDetailRepository;
//    }
//
//    public List<OrdersDetail> getAllOrdersDetails() {
//        return ordersDetailRepository.findAll();
//    }
//
//    public List<OrdersDetail> getOrdersDetailsByOrderId(Integer orderId) {
//        return ordersDetailRepository.findByOrder_Id(orderId);
//    }
//
//    public long calculateTotalRevenue() {
//        List<OrdersDetail> ordersDetails = ordersDetailRepository.findAll();
//        long totalRevenue = 0;
//        for (OrdersDetail ordersDetail : ordersDetails) {
//            totalRevenue += ordersDetail.getPrice() * ordersDetail.getQuantity();
//        }
//        return totalRevenue;
//    }
//
//    public int calculateTotalQuantitySold() {
//        List<OrdersDetail> ordersDetails = ordersDetailRepository.findAll();
//        int totalQuantitySold = 0;
//        for (OrdersDetail ordersDetail : ordersDetails) {
//            totalQuantitySold += ordersDetail.getQuantity();
//        }
//        return totalQuantitySold;
//    }
//
//    public int calculateQuantitySoldByProductId(Integer productId) {
//        List<OrdersDetail> ordersDetails = ordersDetailRepository.findByOrder_Id(productId);
//        int quantitySold = 0;
//        for (OrdersDetail ordersDetail : ordersDetails) {
//            quantitySold += ordersDetail.getQuantity();
//        }
//        return quantitySold;
//    }

    private final OrderDetailRepository ordersDetailRepository;

    public OrdersDetailService(OrderDetailRepository ordersDetailRepository) {
        this.ordersDetailRepository = ordersDetailRepository;
    }

    public List<OrdersDetail> getAllOrdersDetails() {
        return ordersDetailRepository.findAll();
    }

    public List<OrdersDetail> getOrdersDetailsByOrderId(Integer orderId) {
        return ordersDetailRepository.findByOrder_Id(orderId);
    }

    public long calculateTotalRevenue() {
        List<OrdersDetail> ordersDetails = ordersDetailRepository.findAll();
        long totalRevenue = 0;
        for (OrdersDetail ordersDetail : ordersDetails) {
            totalRevenue += ordersDetail.getPrice() * ordersDetail.getQuantity();
        }
        return totalRevenue;
    }

    public int calculateTotalQuantitySold() {
        List<OrdersDetail> ordersDetails = ordersDetailRepository.findAll();
        int totalQuantitySold = 0;
        for (OrdersDetail ordersDetail : ordersDetails) {
            totalQuantitySold += ordersDetail.getQuantity();
        }
        return totalQuantitySold;
    }

//    public int calculateQuantitySoldByProductId(Integer productId) {
//        List<OrdersDetail> ordersDetails = ordersDetailRepository.findByOrder_Id(productId);
//        int quantitySold = 0;
//        for (OrdersDetail ordersDetail : ordersDetails) {
//            quantitySold += ordersDetail.getQuantity();
//        }
//        return quantitySold;
//    }

    public int calculateQuantitySoldByProductId(Integer orderId) {
        List<OrdersDetail> ordersDetails = ordersDetailRepository.findByOrder_Id(orderId);
        int quantitySold = 0;
        for (OrdersDetail ordersDetail : ordersDetails) {
            quantitySold += ordersDetail.getQuantity();
        }
        return quantitySold;
    }

    public List<OrdersDetail> getOrdersDetailsByProductId(Integer orderId) {
        return ordersDetailRepository.findByOrder_Id(orderId);
    }


}
