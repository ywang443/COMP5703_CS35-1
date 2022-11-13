package com.ezeat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ezeat.entity.Messages;
import com.ezeat.vo.ShopMessageVo;
import com.ezeat.vo.UserCollectionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessagesMapper extends BaseMapper<Messages> {
    @Select("select m.*, u.img from messages as m, users as u where m.user_id=${userId} order by m.created_at desc")
    List<ShopMessageVo> getMessages(@Param("userId") int userId);
}
