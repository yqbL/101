package com.example.springboot_101.controller;

import com.example.springboot_101.dto.MedicineOrderDTO;
import com.example.springboot_101.model.MedicineOrder;
import com.example.springboot_101.model.User;
import com.example.springboot_101.service.MedicineOrderService;
import com.example.springboot_101.service.UserService;
import com.example.springboot_101.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*; // 确保导入 java.util.List

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private MedicineOrderService medicineOrderService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String password = request.get("password");

        // 查询用户
        User user = userService.findByPhone(phone);
        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "手机号或密码错误"
            ));
        }

        // 登录成功返回数据
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "登录成功",
                "data", Map.of("userId", user.getId())
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String phone = request.get("phone");
        String password = request.get("password");

        // 检查手机号是否已存在
        if (userService.existsByPhone(phone)) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", "手机号已被注册"
            ));
        }

        // 注册用户
        User user = userService.register(phone, password);
        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "注册成功",
                "data", Map.of(
                        "userId", user.getId(),
                        "phone", user.getPhone()
                )
        ));
    }

    // 获取药单列表
    @GetMapping("/medicine-lists")
    public ResponseEntity<Map<String, Object>> getAllMedicineOrders() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<MedicineOrder> medicineOrders = medicineOrderService.getAllMedicineOrders();
            List<MedicineOrderDTO> medicineOrderDTOs = DtoConverter.convertToMedicineOrderDTOList(medicineOrders);
            if (medicineOrderDTOs.isEmpty()) {
                response.put("success", false);
                response.put("message", "暂无药单数据");
            } else {
                response.put("success", true);
                response.put("message", "获取药单列表成功");
                response.put("data", medicineOrderDTOs);
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取药单列表过程中出现错误，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // 根据药单编号（id）获取药单详情
    @GetMapping("/medicine-order/{id}")
    public ResponseEntity<Map<String, Object>> getMedicineOrder(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            MedicineOrder medicineOrder = medicineOrderService.getMedicineOrderById(id);
            if (medicineOrder == null) {
                response.put("success", false);
                response.put("message", "未找到该药单");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            MedicineOrderDTO medicineOrderDTO = DtoConverter.convertToMedicineOrderDTO(medicineOrder);
            response.put("success", true);
            response.put("message", "获取药单详情成功");
            response.put("data", medicineOrderDTO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "获取药单详情过程中出现错误，请稍后重试");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
