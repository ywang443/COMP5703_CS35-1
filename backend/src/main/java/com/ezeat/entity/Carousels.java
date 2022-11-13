package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 首页轮播图
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Carousels {
    @TableId(type = IdType.AUTO)
    private Integer id; // 轮播图id
    private String img; // 轮播图图片
    @TableField(value = "shop_id")
    private Integer shopId; // 店铺id
    private Integer sort; // 排序
    @TableField(value = "created_at")
    private Date createdAt; // 创建日期
}
