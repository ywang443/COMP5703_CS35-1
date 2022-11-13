package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * 搜索
 */
@Data
public class CategoryVd {
    @NotBlank(message = "类别不能不能为空")
    private String type;
}
