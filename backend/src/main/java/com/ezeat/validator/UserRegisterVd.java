package com.ezeat.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserRegisterVd {
    @NotEmpty(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码不能小于6位")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String mailbox;
    @NotBlank(message = "头像不能为空")
    private String userImg;
}
