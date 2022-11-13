package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 店铺(餐厅)
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Shops {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; // 店铺名称
    @TableField(value = "user_id")
    private Integer userId; // 用户id
    private String img; // 图片
    private String phone; // 电话
    private String rate; // 评分
    private String address; // 地址
    private String description; // 描述
    private String comment; // 评论数
    @TableField(value = "per_room")
    private String perRoom; // 房间平均消费
    private String average;  // 人均消费
    private String icons; // 设施图标
    @TableField(value = "created_at")
    private Date createdAt;
}
