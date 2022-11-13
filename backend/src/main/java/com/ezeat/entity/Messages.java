package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 信息(邮件)
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Messages {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "shop_id")
    private Integer shopId; // 店铺id
    @TableField(value = "user_id")
    private Integer userId; // 用户id
    private String title; // 标题
    private String content; // 内容
    @TableField(value = "is_read")
    private Integer isRead; // 是否已读
    private Integer type; // 类型 0 all 所有, 1 booking 预定, 2 promo 促销
    private Integer status; //  状态 0: 成功  1: 失败
    @TableField(value = "created_at")
    private String createdAt;
}
