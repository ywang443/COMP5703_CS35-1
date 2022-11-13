package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 预约(订单)
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Appointments {
    @TableId(type = IdType.AUTO)
    private Integer id; // 预定id
    @TableField(value = "shop_id")
    private Integer shopId; // 店铺id
    @TableField(value = "user_id")
    private Integer userId; // 用户id
    @TableField(value = "table_id")
    private Integer tableId; // 餐桌id
    private String name; // 客户姓名
    private String quantity; // 用餐人数
    private String tables; // 用餐餐桌
    private String phone; // 客户手机
    private String mail; // 用户邮件
    private String memo; // 用户备注
    @TableField(value = "created_at")
    private Date createdAt; // 创建日期
    private String date; // 预定时间
}
