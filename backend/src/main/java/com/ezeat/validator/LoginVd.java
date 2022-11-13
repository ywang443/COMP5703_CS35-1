package com.ezeat.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 登陆参数较验
 */
@Data
public class LoginVd {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String name;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码不能小于6位")
    private String password;
}
