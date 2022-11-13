package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Hots;
import com.ezeat.entity.Shops;
import com.ezeat.validator.CategoryVd;
import com.ezeat.validator.ShopMessageListVd;
import com.ezeat.vo.ResultVo;

import java.util.List;

public interface ShopsService extends IService<Shops> {
    List getAll();

    /**
     * 获取评分排前${quantity}名的店铺
     *
     * @param quantity
     * @return
     */
    List getHots(int quantity);

    /**
     * 获取热门餐厅的名称列表
     *
     * @return
     */
    List getHotsPlaces(int quantity);

    ResultVo getMessages(ShopMessageListVd shopMessageListVd);

    ResultVo getHomeData();

    ResultVo getCategoryList(CategoryVd categoryVd);
}
