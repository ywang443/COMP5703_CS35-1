package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.entity.Appointments;
import com.ezeat.mapper.AppointmentsMapper;
import com.ezeat.service.AppointmentsService;
import com.ezeat.service.ShopsService;
import com.ezeat.validator.ShopAppointmentDetailVd;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.ShopOrderVo;
import com.ezeat.vo.TokenVo;
import com.ezeat.vo.UserOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class AppointmentsServiceImpl extends ServiceImpl<AppointmentsMapper, Appointments> implements AppointmentsService {
    @Autowired
    private AppointmentsMapper appointmentsMapper;

    @Autowired
    private ShopsService shopsService;

    @Override
    public List getAll() {
        QueryWrapper<Appointments> objectQueryWrapper = new QueryWrapper<>();
        // 选择前五个
//        objectQueryWrapper.last("limit 5");
        List<Appointments> appointments = appointmentsMapper.selectList(objectQueryWrapper);
        return appointments;
    }

    @Override
    public ResultVo getByUserId(int userId) {
        // 当前页
        int current = 1;
        // 每页条数
        int size = this.count(); // 所有条数，相当于不分页
        IPage page = new Page(current, size);
        IPage<UserOrderVo> orderPagination = appointmentsMapper.getByUserId(userId, page);

        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("orders", orderPagination.getRecords()); //  结果列表
        resHashMap.put("current", orderPagination.getCurrent()); // 当前页码
        resHashMap.put("pages", orderPagination.getPages()); // 最后一页
        resHashMap.put("total", orderPagination.getTotal()); // 总记录数

        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getByShopId(int shopId) {
        // 当前页
        int current = 1;
        // 每页条数
        int size = this.count(); // 所有条数，相当于不分页
        IPage page = new Page(current, size);
        IPage<ShopOrderVo> orderPagination = appointmentsMapper.getByShopId(shopId, page);

        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("orders", orderPagination.getRecords()); //  结果列表
        resHashMap.put("current", orderPagination.getCurrent()); // 当前页码
        resHashMap.put("pages", orderPagination.getPages()); // 最后一页
        resHashMap.put("total", orderPagination.getTotal()); // 总记录数
        resHashMap.put("shopId", shopId); // 总记录数

        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getDetailById(ShopAppointmentDetailVd shopAppointmentDetailVd) {
        Integer appointmentId = shopAppointmentDetailVd.getId();
        String token = shopAppointmentDetailVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        int shopId = tokenVo.getShopId();

        QueryWrapper<Appointments> appointmentsQueryWrapper = new QueryWrapper<>();
        appointmentsQueryWrapper.eq("id", appointmentId);
        appointmentsQueryWrapper.eq("shop_id", shopId);
        Appointments appointment = appointmentsMapper.selectOne(appointmentsQueryWrapper);

        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("orderDetail",appointment);
        return  new ResultVo(resHashMap);
    }
}
