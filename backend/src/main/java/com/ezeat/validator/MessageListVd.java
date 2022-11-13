package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 获取用户信息列表api的参数
 */
@Data
public class MessageListVd extends TokenVd {
    @NotNull(message = "type不能为空")
    private Integer type;
}
