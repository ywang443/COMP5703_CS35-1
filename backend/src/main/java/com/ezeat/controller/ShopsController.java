package com.ezeat.controller;

import com.ezeat.common.util.TokenUtils;
import com.ezeat.service.AppointmentsService;
import com.ezeat.service.MessagesService;
import com.ezeat.service.ShopsService;
import com.ezeat.service.TablesService;
import com.ezeat.validator.*;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/shop")
public class ShopsController {
    @Autowired
    ShopsService shopsService;

    @Autowired
    TablesService tablesService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    AppointmentsService appointmentsService;

    /**
     * 获取首页数据
     * @return
     */
    @GetMapping("/home")
    public ResultVo home() {
        return shopsService.getHomeData();
    }

    /**
     * 更改桌子状态
     *
     * @return
     */
    @PostMapping("/table")
    public ResultVo changeTableStatus(TableStatusVd tableStatusVd) {
        return tablesService.changeTableStatus(tableStatusVd);
    }

    /**
     * 获取商家邮件
     *
     * @return
     */
    @PostMapping("/mail")
    public ResultVo getShopMessages(ShopMessageListVd shopMessageListVd) {
        return shopsService.getMessages(shopMessageListVd);
    }

    /**
     * 更改邮件状态
     *
     * @return
     */
    @PostMapping("/mail/status")
    public ResultVo updateMessageIsRead(ShopMessageUpdateIsReadVd shopMessageUpdateIsReadVd) {
        return messagesService.updateMessageIsRead(shopMessageUpdateIsReadVd);
    }

    @PostMapping("/order")
    public ResultVo getOrderList(TokenVd tokenVd) {
        TokenVo tokenVo = TokenUtils.tokenToObject(tokenVd.getToken());
        return appointmentsService.getByShopId(tokenVo.getShopId());
    }

    /**
     * 获取订单详细信息
     *
     * @return
     */
    @PostMapping("/order/info")
    public ResultVo getAppointmentDetail(ShopAppointmentDetailVd shopAppointmentDetailVd) {
        return appointmentsService.getDetailById(shopAppointmentDetailVd);
    }
}
