package com.ezeat.validator;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 添加订单接口到参数
 */
@Data
public class AppointmentAddVd extends TokenVd {
    @NotNull(message = "店铺id不能为空")
    private Integer shopId;

    @NotBlank(message = "预定时间不能为空")
    private String dateValue;

    @NotBlank(message = "用餐人数不能为空")
    private String haveMealsGuests;

    @NotBlank(message = "用餐餐桌数不能为空")
    private String haveMealsTables;

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "邮箱不能为空")
    private String mail;

    @NotBlank(message = "手机不能为空")
    private String phone;

//    @NotBlank(message = "留言不能为空")
    private String message;
}

