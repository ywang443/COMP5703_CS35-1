package com.ezeat.validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class ShopRegisterVd {
    @NotEmpty(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, message = "密码不能小于6位")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String mailBox;
    @NotBlank(message = "手机不能为空")
    private String shopPhone;
    @NotBlank(message = "店铺名不能为空")
    private String shopName;
    @NotBlank(message = "店铺地址不能为空")
    private String shopAddress;
    @NotBlank(message = "店铺描述不能为空")
    private String shopDescription;
    @NotBlank(message = "店铺图片不能为空")
    private String shopImg;
    @NotBlank(message = "头像不能为空")
    private String userImg;
}
