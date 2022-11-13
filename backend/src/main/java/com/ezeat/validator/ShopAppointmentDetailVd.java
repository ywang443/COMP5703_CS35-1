package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * getAppointmentDetail 参数校验器
 */
@Data
public class ShopAppointmentDetailVd  extends TokenVd{
    @NotNull(message = "预定id不能为空")
    private Integer id;
}
