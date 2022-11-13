package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.entity.Collections;
import com.ezeat.mapper.CollectionsMapper;
import com.ezeat.service.CollectionsService;
import com.ezeat.service.ShopsService;
import com.ezeat.validator.CollectionTogglleVd;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import com.ezeat.vo.UserCollectionVo;
import com.ezeat.vo.UserOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class CollectionsServiceImpl extends ServiceImpl<CollectionsMapper, Collections> implements CollectionsService {
    @Autowired
    private CollectionsMapper collectionsMapper;

    @Override
    public List getAll() {
        QueryWrapper<Collections> collectionsQueryWrapper = new QueryWrapper<>();
        // 升序排列
        collectionsQueryWrapper.orderByAsc("sort");
        List<Collections> collections = collectionsMapper.selectList(collectionsQueryWrapper);
        return collections;
    }

    @Override
    public List checkExist(CollectionTogglleVd collectionTogglleVd) {
        return null;
    }

    @Override
    public ResultVo toggleCollection(CollectionTogglleVd collectionTogglleVd) {

        // 获取并解析token
        String token = collectionTogglleVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);

        // 构建收藏查询器，查找包含当前用户，以及目标店铺的记录。
        QueryWrapper<Collections> collectionsQueryWrapper = new QueryWrapper<>();
        HashMap<String, Integer> idHashMap = new HashMap<>();
        int shopId = Integer.parseInt(collectionTogglleVd.getShopId());
        idHashMap.put("user_id", tokenVo.getUserId());
        idHashMap.put("shop_id", shopId);
        collectionsQueryWrapper.allEq(idHashMap);
        Collections collection = this.getOne(collectionsQueryWrapper);

        // 如果记录不存在, 插入
        if (collection == null) {
            Collections nCollection = new Collections();
            nCollection.setUserId(tokenVo.getUserId());
            nCollection.setShopId(shopId);
            collectionsMapper.insert(nCollection);
            return new ResultVo("收藏成功");
        }
        // 如果记录存在，删除
        collectionsMapper.deleteById(collection.getId());
        return new ResultVo("已取消收藏");
    }

    @Override
    public ResultVo getCollections(String token) {
        // 获取并解析token
        TokenVo tokenVo = TokenUtils.tokenToObject(token);

        // 获取用户收藏列表
        List<UserCollectionVo> collections = collectionsMapper.getByUserId(tokenVo.getUserId());

        // 构建返回给前端的数据
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("collections", collections);
        return new ResultVo(resHashMap);
    }
}
