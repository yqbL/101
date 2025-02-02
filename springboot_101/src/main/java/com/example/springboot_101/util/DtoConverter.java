package com.example.springboot_101.util;

import com.example.springboot_101.dto.MedicineItemDTO;
import com.example.springboot_101.dto.MedicineOrderDTO;
import com.example.springboot_101.model.MedicineItem;
import com.example.springboot_101.model.MedicineOrder;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    /**
     * 将 MedicineOrder 实体转换为 MedicineOrderDTO
     * @param medicineOrder MedicineOrder 实体对象
     * @return MedicineOrderDTO 对象
     */
    public static MedicineOrderDTO convertToMedicineOrderDTO(MedicineOrder medicineOrder) {
        if (medicineOrder == null) {
            return null;
        }
        List<MedicineItemDTO> itemDTOs = new ArrayList<>();
        if (medicineOrder.getItems() != null) {
            for (MedicineItem item : medicineOrder.getItems()) {
                itemDTOs.add(convertToMedicineItemDTO(item));
            }
        }
        return new MedicineOrderDTO(
                medicineOrder.getId(),
                medicineOrder.getTime(),
                medicineOrder.getHospital(),
                itemDTOs
        );
    }

    /**
     * 将 MedicineItem 实体转换为 MedicineItemDTO
     * @param medicineItem MedicineItem 实体对象
     * @return MedicineItemDTO 对象
     */
    public static MedicineItemDTO convertToMedicineItemDTO(MedicineItem medicineItem) {
        if (medicineItem == null) {
            return null;
        }
        System.out.println("Converting MedicineItem: " + medicineItem);
        MedicineItemDTO itemDTO = new MedicineItemDTO(
                medicineItem.getId(),
                medicineItem.getProductName(),
                medicineItem.getChemicalName(),
                medicineItem.getDose(),
                medicineItem.getTime(),
                medicineItem.getStatus()
        );
        System.out.println("Converted MedicineItemDTO: " + itemDTO);
        return itemDTO;
    }

    /**
     * 将 MedicineOrder 实体列表转换为 MedicineOrderDTO 列表
     * @param medicineOrders MedicineOrder 实体列表
     * @return MedicineOrderDTO 列表
     */
    public static List<MedicineOrderDTO> convertToMedicineOrderDTOList(List<MedicineOrder> medicineOrders) {
        if (medicineOrders == null) {
            return null;
        }
        List<MedicineOrderDTO> medicineOrderDTOs = new ArrayList<>();
        for (MedicineOrder medicineOrder : medicineOrders) {
            medicineOrderDTOs.add(convertToMedicineOrderDTO(medicineOrder));
        }
        return medicineOrderDTOs;
    }
}