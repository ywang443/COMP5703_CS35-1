package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Tables;
import com.ezeat.validator.ShopMessageListVd;
import com.ezeat.validator.TableStatusVd;
import com.ezeat.vo.ResultVo;

import java.util.List;

public interface TablesService extends IService<Tables> {
    List getAll();

    ResultVo changeTableStatus(TableStatusVd tableStatusVd);

    ResultVo getShopMessages(ShopMessageListVd shopMessageListVd);
}
