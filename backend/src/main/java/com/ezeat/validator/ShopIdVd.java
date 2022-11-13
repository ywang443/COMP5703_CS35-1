package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 需要用到token的都继承这个
 */
@Data
public class ShopIdVd {
    @NotNull(message = "餐厅id不能为空")
    private Integer id;
}
