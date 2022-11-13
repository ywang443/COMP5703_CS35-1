package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Hots;

import java.util.List;

public interface HotsService  extends IService<Hots> {
    List getAll();

}
