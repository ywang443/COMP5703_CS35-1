package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * 更改桌子状态参数验证器
 */
@Data
public class TableStatusVd extends TokenVd {
    @NotNull(message = "id不能为空")
    private Integer id;

    @NotNull(message = "status不能为空")
    private Integer status;
}
