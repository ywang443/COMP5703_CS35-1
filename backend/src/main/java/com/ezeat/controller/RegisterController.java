package com.ezeat.controller;

import com.ezeat.service.*;
import com.ezeat.validator.*;
import com.ezeat.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    ShopsService shopsService;

    @Autowired
    UsersService usersService;

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/user")
    public ResultVo user(@Validated UserRegisterVd userRegisterVd) {
        return usersService.registerUser(userRegisterVd);
    }

    /**
     * 商家注册
     * @param shopRegisterVd
     * @return
     */
    @PostMapping("/shop")
    public ResultVo shop(@Validated ShopRegisterVd shopRegisterVd) {
        return usersService.registerShop(shopRegisterVd);
    }
}
