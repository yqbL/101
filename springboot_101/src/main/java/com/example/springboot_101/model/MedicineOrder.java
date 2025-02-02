package com.example.springboot_101.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "medicineorder") // 明确指定数据库表名
public class MedicineOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String time;
    private String hospital;

    @OneToMany(mappedBy = "medicineOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MedicineItem> items;

    // Getters and Setters
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

    public List<MedicineItem> getItems() {
        return items;
    }

    public void setItems(List<MedicineItem> items) {
        this.items = items;
    }
}