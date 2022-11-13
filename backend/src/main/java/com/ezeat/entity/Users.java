package com.ezeat.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;


/**
 * 用户
 */
@Data // 生成getter,setter ,toString等函数
@NoArgsConstructor // 生成无参构造函数
@AllArgsConstructor //生成全参数构造函数
public class Users {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name; // 姓名
    private String img; // 头像
    private String email; // 邮箱
    private String password; // 密码
    @TableField(value = "created_at")
    private Date createdAt; // 创建日期
}
