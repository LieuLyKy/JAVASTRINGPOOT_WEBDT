package com.example.WebDT.repository;

import com.example.WebDT.entity.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrdersDetail, Long> {
    List<OrdersDetail> findByOrder_Id(Integer orderId);

}
