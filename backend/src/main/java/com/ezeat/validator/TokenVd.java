package com.ezeat.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 需要用到token的都继承这个
 */
@Data
public class TokenVd {
    @NotBlank(message = "token不能为空")
    private String token;
}
