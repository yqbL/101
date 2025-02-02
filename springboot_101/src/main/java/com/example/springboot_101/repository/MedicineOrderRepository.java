package com.example.springboot_101.repository;

import com.example.springboot_101.model.MedicineOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicineOrderRepository extends JpaRepository<MedicineOrder, Long> {
    //MedicineOrder findByOrderNumber(String orderNumber);
}
