package com.ezeat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ezeat.entity.Collections;
import com.ezeat.vo.UserCollectionVo;
import com.ezeat.vo.UserOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionsMapper extends BaseMapper<Collections> {

    @Select("select s.*, c.id as cid from collections c, shops s where c.shop_id=s.id and c.user_id=${userId}")
    List<UserCollectionVo> getByUserId(@Param("userId") int userId);
}
