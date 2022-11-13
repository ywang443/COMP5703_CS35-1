package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Collections;
import com.ezeat.validator.CollectionTogglleVd;
import com.ezeat.vo.ResultVo;

import java.util.List;

public interface CollectionsService extends IService<Collections> {
    List getAll();

    /**
     * 查看收藏是否存在
     */
    List checkExist(CollectionTogglleVd collectionTogglleVd);

    /**
     * 切换收藏状态
     */
    ResultVo toggleCollection(CollectionTogglleVd collectionTogglleVd);

    /**
     * 获取用户收藏列表
     */
    ResultVo getCollections(String token);
}
