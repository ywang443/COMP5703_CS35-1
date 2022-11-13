package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 获取用户信息列表api的参数
 */
@Data
public class MessageDetailVd extends TokenVd {
    @NotNull(message = "id不能为空")
    private Integer id;
}
