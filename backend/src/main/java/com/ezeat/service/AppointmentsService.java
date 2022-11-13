package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Appointments;
import com.ezeat.validator.ShopAppointmentDetailVd;
import com.ezeat.vo.ResultVo;

import java.util.List;

public interface AppointmentsService extends IService<Appointments> {
    List getAll();

    ResultVo getByUserId(int userId);

    ResultVo getByShopId(int shopId);

    ResultVo getDetailById(ShopAppointmentDetailVd shopAppointmentDetailVd);

}
