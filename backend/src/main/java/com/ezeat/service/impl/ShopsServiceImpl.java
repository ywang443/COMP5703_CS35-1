package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.entity.Appointments;
import com.ezeat.entity.Hots;
import com.ezeat.entity.Messages;
import com.ezeat.entity.Shops;
import com.ezeat.mapper.HotsMapper;
import com.ezeat.mapper.ShopsMapper;
import com.ezeat.service.AppointmentsService;
import com.ezeat.service.HotsService;
import com.ezeat.service.MessagesService;
import com.ezeat.service.ShopsService;
import com.ezeat.validator.CategoryVd;
import com.ezeat.validator.ShopMessageListVd;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ShopsServiceImpl extends ServiceImpl<ShopsMapper, Shops> implements ShopsService {
    @Autowired
    private ShopsMapper shopsMapper;

    @Autowired
    private AppointmentsService appointmentsService;

    @Autowired
    private MessagesService messagesService;

    @Override
    public List getAll() {
        QueryWrapper<Shops> objectQueryWrapper = new QueryWrapper<>();
        // 选择前五个
        objectQueryWrapper.last("limit 5");
        List<Shops> shops = shopsMapper.selectList(objectQueryWrapper);
        return shops;
    }

    @Override
    public List getHots(int quantity) {
        QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
        // 选择需要的字段
        shopsQueryWrapper.select("id", "img", "name", "description", "rate", "average");
        // 按照评分排序
        shopsQueryWrapper.orderByDesc("rate");
        // 选择前五个
        shopsQueryWrapper.last("limit " + quantity);
        List<Map<String, Object>> shops = shopsMapper.selectMaps(shopsQueryWrapper);
        return shops;

    }

    @Override
    public List getHotsPlaces(int quantity) {
        QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
        // 选择需要的字段
        shopsQueryWrapper.select("name");
        // 按照评分排序
        shopsQueryWrapper.orderByDesc("rate");
        shopsQueryWrapper.last("limit " + quantity);
//        List<Map<String, Object>> hotShops = shopsMapper.selectMaps(shopsQueryWrapper);
        List<Shops> hotShops = shopsMapper.selectList(shopsQueryWrapper);
        ArrayList<String> hotPlaces = new ArrayList<>();
        hotShops.forEach(hot -> {
            hotPlaces.add(hot.getName());
        });
        return hotPlaces;
    }

    @Override
    public ResultVo getMessages(ShopMessageListVd shopMessageListVd) {
        String token = shopMessageListVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        int shopId = tokenVo.getShopId();
        int userId = tokenVo.getUserId();
        Integer messageType = shopMessageListVd.getType();

        QueryWrapper<Messages> messagesQueryWrapper = new QueryWrapper<>();
        messagesQueryWrapper.eq("shop_id", shopId);
        messagesQueryWrapper.eq("type", messageType);
//        List<Messages> messages = messagesService.list(messagesQueryWrapper);
        List messages = messagesService.getMessages(userId);
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("mails", messages);
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getHomeData() {
        HashMap<String, Object> resHashMap = new HashMap<>();

        // 季度销售额数据
        HashMap<String, Object> reserveSaleData = new HashMap<>();
        ArrayList<String> dateList = new ArrayList<>();
        ArrayList<Integer> moneyList = new ArrayList<>();
        dateList.add("一季度");
        moneyList.add(54268);
        dateList.add("二季度");
        moneyList.add(84651);
        dateList.add("三季度");
        moneyList.add(64368);
        dateList.add("四季度");
        moneyList.add(52185);
        reserveSaleData.put("date", dateList);
        reserveSaleData.put("money", moneyList);
        resHashMap.put("reserveSaleData", reserveSaleData);
        // 周销售额
        HashMap<String, Object> salesVolumeData = new HashMap<>();
        ArrayList<String> salesVolumeDateList = new ArrayList<>();
        ArrayList<Integer> salesVolumeMoneyList = new ArrayList<>();
        salesVolumeDateList.add("周一");
        salesVolumeMoneyList.add(500);
        salesVolumeDateList.add("周二");
        salesVolumeMoneyList.add(600);
        salesVolumeDateList.add("周三");
        salesVolumeMoneyList.add(530);
        salesVolumeDateList.add("周四");
        salesVolumeMoneyList.add(542);
        salesVolumeDateList.add("周五");
        salesVolumeMoneyList.add(345);
        salesVolumeDateList.add("周六");
        salesVolumeMoneyList.add(563);
        salesVolumeDateList.add("周日");
        salesVolumeMoneyList.add(754);
        salesVolumeData.put("date", salesVolumeDateList);
        salesVolumeData.put("money", salesVolumeMoneyList);
        resHashMap.put("salesVolume", salesVolumeData);

        // 月预定
        HashMap<String, Object> monthReserveData = new HashMap<>();
        ArrayList<String> monthReserveDateList = new ArrayList<>();
        ArrayList<Integer> monthReserveQuantityList = new ArrayList<>();
        monthReserveDateList.add("上旬");
        monthReserveQuantityList.add(554);
        monthReserveDateList.add("中旬");
        monthReserveQuantityList.add(547);
        monthReserveDateList.add("下旬");
        monthReserveQuantityList.add(475);
        monthReserveData.put("date", monthReserveDateList);
        monthReserveData.put("quantity", monthReserveQuantityList);
        resHashMap.put("monthReserve", monthReserveData);
        // 小桌预定数
        resHashMap.put("smallTable", 20);
        // 中桌预定数
        resHashMap.put("inTable", 40);
        // 大桌预定数
        resHashMap.put("largeTable", 55);

        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getCategoryList(CategoryVd categoryVd) {
        QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>();
        shopsQueryWrapper.select("id", "name", "img", "description", "address", "rate", "average");
        shopsQueryWrapper.eq("type", categoryVd.getType());
//        List<Shops> shops = shopsMapper.selectList(shopsQueryWrapper);

        List<Map<String, Object>> shops = this.listMaps(shopsQueryWrapper);

        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("shopList", shops);
        resHashMap.put("type", categoryVd.getType());
        return new ResultVo(resHashMap);
    }
}
