package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Carousels;

import java.util.List;

public interface CarouselsService extends IService<Carousels> {
    List getAll();
}
