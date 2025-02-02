package com.example.springboot_101.dto;

import java.util.List;

public class MedicineOrderDTO {
    private Long id;
    private String time;
    private String hospital;
    private List<MedicineItemDTO> items;

    // 构造函数
    public MedicineOrderDTO() {
    }

    public MedicineOrderDTO(Long id, String time, String hospital, List<MedicineItemDTO> items) {
        this.id = id;
        this.time = time;
        this.hospital = hospital;
        this.items = items;
    }

    // Getters 和 Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public List<MedicineItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MedicineItemDTO> items) {
        this.items = items;
    }
}