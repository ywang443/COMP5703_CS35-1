package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 切换收藏餐厅的参数
 */
@Data
public class CollectionTogglleVd extends TokenVd {
    @NotBlank(message = "shopId不能为空")
    private String shopId;
}
