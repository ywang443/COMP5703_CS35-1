package com.ezeat.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ezeat.entity.Appointments;
import com.ezeat.entity.Shops;
import com.ezeat.vo.ShopOrderVo;
import com.ezeat.vo.UserOrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;

import java.util.List;

@Mapper
public interface AppointmentsMapper extends BaseMapper<Appointments> {
    // 这里为了方便就用了注解形式  XML形式也一样,sql要自己写
    @Select("select s.name as shopName, s.address, s.img, a.phone, a.name, a.mail, a.memo as message,  a.id, a.quantity, a.created_at as createdAt,a.date, u.id as userId from appointments a, shops s, users u where a.shop_id=s.id and u.id=${userId} and a.user_id=${userId} order by a.created_at desc")
    IPage<UserOrderVo> getByUserId(@Param("userId") int userId, IPage page);

    @Select("select t.id as tableId, t.img, t.status, t.name as tableName, a.id, a.date as time, a.price, a.shop_id as shopId, a.created_at as createdAt from tables as t, appointments as a where  t.shop_id=${shopId} and a.shop_id=${shopId} order by a.created_at desc")
//    @Select("select a.* from tables as t, appointments as a where  t.shop_id=${shopId} and a.shop_id=${shopId} order by a.created_at desc")
    IPage<ShopOrderVo> getByShopId(@Param("shopId") int shopId, IPage page);
}
