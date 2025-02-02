package com.example.springboot_101.service;

import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.repository.MedicineOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineOrderService {

    @Autowired
    private MedicineOrderRepository medicineOrderRepository;

    public List<MedicineOrder> getAllMedicineOrders() {
        return medicineOrderRepository.findAll();
    }

    public MedicineOrder getMedicineOrderById(Long id) {
        return medicineOrderRepository.findById(id).orElse(null);
    }
}
