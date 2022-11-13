package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 桌子
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Tables {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; // 桌子名称
    @TableField(value = "shop_id")
    private Integer shopId; // 店铺id
    private Integer status; // 桌子状态  0: 可用   1: 不可用
    private Integer type; // 桌子大小  0: 大, 1: 中,  2: 小
    @TableField(value = "created_at")
    private Date createdAt;
}
