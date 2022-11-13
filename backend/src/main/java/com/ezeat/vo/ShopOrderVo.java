package com.ezeat.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderVo {
    private int id;
    private int tableId;
    private int shopId;
    private Boolean status;
    private String img;
    private String tableName;
    private String time;
    private String price;
    private String createdAt;
}
