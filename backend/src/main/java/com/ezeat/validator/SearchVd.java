package com.ezeat.validator;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


/**
 * 搜索
 */
@Data
public class SearchVd {
    @NotBlank(message = "搜索词不能为空")
    private String value;
}
