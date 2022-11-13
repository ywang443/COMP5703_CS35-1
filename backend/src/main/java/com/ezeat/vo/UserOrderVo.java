package com.ezeat.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOrderVo {
    private int id;
    private int appointmentId;
    private int userId;
    private int ShopId;
    private String ShopName;
    private String img;
    private String name;
    private String mail;
    private String message;
    private String phone;
    private String address;
    private Integer quantity;
    private String createdAt;
    private String date;
}
