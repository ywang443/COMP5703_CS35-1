package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.entity.Carousels;
import com.ezeat.mapper.CarouselsMapper;
import com.ezeat.service.CarouselsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarouselsServiceImpl extends ServiceImpl<CarouselsMapper, Carousels> implements CarouselsService {
    @Autowired
    private CarouselsMapper carouselsMapper;

    @Override
    public List getAll() {
        QueryWrapper<Carousels> carouselsQueryWrapper = new QueryWrapper<>();
        // 升序排列
        carouselsQueryWrapper.orderByAsc("sort");
        List<Carousels> carousels = carouselsMapper.selectList(carouselsQueryWrapper);
        return carousels;
    }
}
