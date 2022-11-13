package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.validator.*;
import com.ezeat.vo.ResultVo;
import com.ezeat.entity.Users;

public interface UsersService extends IService<Users> {
    ResultVo login(LoginVd loginVd);

    ResultVo getHomeData();


    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return
     */
    Users findUserById(Long userId);

    /**
     * 根据用户ID修改用户信息
     *
     * @param users 用户
     * @return
     */
    Users modifyUserById(Users users);

    /**
     * 根据用户名获取用户信息
     *
     * @param name 用户名
     * @return
     */
    Users findUserByUsername(String name);

    /**
     * 验证用户密码
     *
     * @param passwordDb 数据库中的密码
     * @param passwordFe 前端传来的密码
     * @return
     */
    Boolean verifyPassword(String passwordDb, String passwordFe);

    /**
     * 根据用户邮箱获取用户信息
     *
     * @param email 用户邮箱
     * @return
     */
    Users findUserByEmail(String email);

    /**
     * 获取餐厅详情信息
     *
     * @param id
     * @return
     */
    ResultVo getShopDetail(int id);

    /**
     * 获取排行数据
     *
     * @return
     */
    ResultVo getHomeRanking();

    /**
     * 获取订单的餐厅信息
     *
     * @return
     */
    ResultVo orderLower(int id);


    /**
     * 搜索餐厅
     *
     * @return
     */
    ResultVo searchShop(String value);

    /**
     * 搜索页的热门词语
     *
     * @return
     */
    ResultVo searchInfo();

    /**
     * 提交订单
     *
     * @return
     */
    ResultVo orderAdd(AppointmentAddVd appointmentAddVd);

    ResultVo registerUser(UserRegisterVd userRegisterVd);

    ResultVo registerShop(ShopRegisterVd shopRegisterVd);
}
