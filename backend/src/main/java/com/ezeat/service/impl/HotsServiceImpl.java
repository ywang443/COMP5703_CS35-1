package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.entity.Shops;
import com.ezeat.mapper.HotsMapper;
import com.ezeat.entity.Hots;
import com.ezeat.service.HotsService;
import com.ezeat.service.ShopsService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class HotsServiceImpl extends ServiceImpl<HotsMapper, Hots> implements HotsService {
    @Autowired
    private HotsMapper hotsMapper;

    @Autowired
    private ShopsService shopsService;

    @Override
    public List getAll() {
        // 查询条件构造器
        QueryWrapper<Hots> objectQueryWrapper = new QueryWrapper<>();
        // 按照sort字段升序排列
        objectQueryWrapper.orderByAsc("sort");
        // 根据条件从数据库中选择数据
        List<Hots> hots = hotsMapper.selectList(objectQueryWrapper);
        // 热门餐厅列表
        ArrayList<Map> shops = new ArrayList<>();
        // 根据热门餐厅列表来从shops表里面获取餐厅的详细信息
        hots.forEach(hot -> {
            QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
            // 只选择以下几个需要的字段
            shopsQueryWrapper.select("id", "img", "name", "description", "rate", "average");
            shopsQueryWrapper.eq("id", hot.getShopId());
            Map<String, Object> shopMap = shopsService.getMap(shopsQueryWrapper);
            shopMap.put("sort", hot.getSort());
            shops.add(shopMap);
        });
        return shops;
    }
}
