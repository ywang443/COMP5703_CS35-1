package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.constant.statusCode.impl.ResultCode;
import com.ezeat.entity.Shops;
import com.ezeat.entity.Tables;
import com.ezeat.mapper.ShopsMapper;
import com.ezeat.mapper.TablesMapper;
import com.ezeat.service.AppointmentsService;
import com.ezeat.service.TablesService;
import com.ezeat.validator.ShopMessageListVd;
import com.ezeat.validator.TableStatusVd;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class TablesServiceImpl extends ServiceImpl<TablesMapper, Tables> implements TablesService {
    @Autowired
    private TablesMapper tablesMapper;

    @Override
    public List getAll() {
        QueryWrapper<Tables> objectQueryWrapper = new QueryWrapper<>();
        // 选择前五个
        objectQueryWrapper.last("limit 5");
        List<Tables> tables = tablesMapper.selectList(objectQueryWrapper);
        return tables;
    }

    @Override
    public ResultVo changeTableStatus(TableStatusVd tableStatusVd) {
        String token = tableStatusVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        int shopIdToken = tokenVo.getShopId();

        Tables table = tablesMapper.selectById(tableStatusVd.getId());
        if (table == null) {
            return new ResultVo(ResultCode.FAILED, "桌子不存在");
        }

        Integer status = tableStatusVd.getStatus();
        if (status != 0 && status != 1) {
            return new ResultVo(ResultCode.FAILED, "status只能是0或者1");
        }
        table.setStatus(tableStatusVd.getStatus());

        Integer shopIdDb = table.getShopId();
        if (shopIdDb != shopIdToken) {
            return new ResultVo(ResultCode.AUTH_ERROR);
        }

        QueryWrapper<Tables> tablesQueryWrapper = new QueryWrapper<>();
        tablesQueryWrapper.eq("shop_id", tokenVo.getShopId());
        int update = tablesMapper.update(table, tablesQueryWrapper);
//        int effected = tablesMapper.updateById(table);
        if (update == 0) {
            return new ResultVo(ResultCode.UPDATE_ERROR);
        }
        return new ResultVo("更新状态成功");
    }

    @Override
    public ResultVo getShopMessages(ShopMessageListVd shopMessageListVd) {
        return new ResultVo("Test");
    }
}
