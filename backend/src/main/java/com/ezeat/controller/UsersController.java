package com.ezeat.controller;


import com.ezeat.common.util.TokenUtils;
import com.ezeat.service.*;
import com.ezeat.validator.*;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@Validated
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private CollectionsService collectionsService;

    @Autowired
    private AppointmentsService appointmentsService;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private ShopsService shopsService;

    /**
     * 登陆
     *
     * @param loginVd
     * @return
     */
    @PostMapping("/login")
    public ResultVo login(@Valid LoginVd loginVd) {
        System.out.println(loginVd);
        return usersService.login(loginVd);
    }

    /**
     * 获取首页信息
     *
     * @return
     */
    @GetMapping("/home")
    public ResultVo home() {
        return usersService.getHomeData();
    }

    /**
     * 获取餐厅详情信息
     *
     * @return
     */
    @GetMapping("/restaurant")
    public ResultVo restaurant(@Valid ShopIdVd shopIdVd) {
        return usersService.getShopDetail((shopIdVd.getId()));
    }

    /**
     * 获取排行数据
     *
     * @return
     */
    @GetMapping("/home/ranking")
    public ResultVo homeRanking() {
        return usersService.getHomeRanking();
    }

    /**
     * 获取下单餐厅信息
     *
     * @return
     */
    @GetMapping("/order/lower")
    public ResultVo orderLower(@Valid ShopIdVd shopIdVd) {
        return usersService.orderLower(shopIdVd.getId());
    }

    /**
     * 搜索餐厅
     *
     * @return
     */
    @GetMapping("/search")
    public ResultVo search(@Valid SearchVd searchVd) {
        return usersService.searchShop(searchVd.getValue());
    }

    /**
     * 搜索页的热门词语
     *
     * @return
     */
    @GetMapping("/search/info")
    public ResultVo searchInfo() {
        return usersService.searchInfo();
    }

    /**
     * 获取用户订单列表
     *
     * @return
     */
    @PostMapping("/order")
    public ResultVo getOrderList(@Valid TokenVd tokenVd) {
        TokenVo tokenVo = TokenUtils.tokenToObject(tokenVd.getToken());
        return appointmentsService.getByUserId(tokenVo.getUserId());
    }

    /**
     * 提交订单
     *
     * @return
     */
    @PostMapping("/order/add")
    public ResultVo orderAdd(@Valid AppointmentAddVd appointmentAddVd) {
        return usersService.orderAdd(appointmentAddVd);
    }

    /**
     * 切换收藏店铺
     *
     * @return
     */
    @PostMapping("/search/popular")
    public ResultVo toggleCollection(@Valid CollectionTogglleVd collectionTogglleVd) {
        return collectionsService.toggleCollection(collectionTogglleVd);
    }

    /**
     * 获取用户收藏列表
     *
     * @return
     */
    @PostMapping("/like")
    public ResultVo getCollections(@Valid TokenVd tokenVd) {
        return collectionsService.getCollections(tokenVd.getToken());
    }

    /**
     * 获取用户信息列表
     *
     * @return
     */
    @PostMapping("/mail")
    public ResultVo getUserMessage(@Valid MessageListVd messageListVd) {
        return messagesService.getUserMessagesByType(messageListVd);
    }

    /**
     * 获取用户信息详情
     *
     * @return
     */
    @PostMapping("/mail/info")
    public ResultVo getUserMessageDetail(@Valid MessageDetailVd messageDetailVd) {
        return messagesService.getUserMessageDetail(messageDetailVd);
    }

    /**
     * 获取类别信息
     *
     * @return
     */
    @GetMapping("/category")
    public ResultVo getCategoryList(@Valid CategoryVd categoryVd) {
        return shopsService.getCategoryList(categoryVd);
    }
}
