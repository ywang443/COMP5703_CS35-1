package com.ezeat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezeat.entity.Shops;
import com.ezeat.entity.Users;
import com.ezeat.vo.UserCollectionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopsMapper extends BaseMapper<Shops> {

}
