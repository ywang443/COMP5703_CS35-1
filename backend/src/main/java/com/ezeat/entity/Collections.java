package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 用户收藏
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Collections {
    @TableId(type = IdType.AUTO)
    private Integer id; // 收藏id
    @TableField(value = "shop_id")
    private Integer shopId; // 店铺id
    @TableField(value = "user_id")
    private Integer userId; // 用户id
    @TableField(value = "created_at") // 创建日期
    private Date createdAt;
}
