package com.ezeat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ezeat.common.util.TokenUtils;
import com.ezeat.constant.statusCode.impl.ResultCode;
import com.ezeat.entity.Messages;
import com.ezeat.mapper.MessagesMapper;
import com.ezeat.service.MessagesService;
import com.ezeat.validator.CategoryVd;
import com.ezeat.validator.MessageDetailVd;
import com.ezeat.validator.MessageListVd;
import com.ezeat.validator.ShopMessageUpdateIsReadVd;
import com.ezeat.vo.ResultVo;
import com.ezeat.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class MessagesServiceImpl extends ServiceImpl<MessagesMapper, Messages> implements MessagesService {
    @Autowired
    MessagesMapper messagesMapper;

    @Override
    public List getAll() {
        List<Messages> messages = messagesMapper.selectList(null);
        return messages;
    }

    @Override
    public ResultVo getUserMessagesByType(MessageListVd messageListVd) {
        // 信息类型
        Integer messageType = messageListVd.getType();
        // 解析token
        String token = messageListVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        int userId = tokenVo.getUserId();

        // 构建查询器
        QueryWrapper<Messages> messagesQueryWrapper = new QueryWrapper<>();
        HashMap<String, Object> eqMap = new HashMap<>();
        eqMap.put("user_id", userId);
        if (messageType != 0) {
            eqMap.put("type", messageType);
        }
        messagesQueryWrapper.allEq(eqMap);
        messagesQueryWrapper.select("id", "title", "content", "created_at as createdAt", "type", "is_read as isRead");
        List<Map<String, Object>> messages = messagesMapper.selectMaps(messagesQueryWrapper);
//        // 没有记录
//        if (messages.size() == 0) {
//            return new ResultVo(ResultCode.LIST_EMPTY);
//        }
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("mails", messages);
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo getUserMessageDetail(MessageDetailVd messageDetailVd) {

        // 信息id
        Integer messageId = messageDetailVd.getId();
        // 解析token
        String token = messageDetailVd.getToken();
        TokenVo tokenVo = TokenUtils.tokenToObject(token);
        int userId = tokenVo.getUserId();

        // 构建查询器
        QueryWrapper<Messages> messagesQueryWrapper = new QueryWrapper<>();
        HashMap<String, Object> eqMap = new HashMap<>();
        eqMap.put("user_id", userId);
        eqMap.put("id", messageId);
        messagesQueryWrapper.allEq(eqMap);
//        messagesQueryWrapper.select("id", "title", "content", "created_at as createdAt", "type", "is_read as isRead");
//        List<Map<String, Object>> messages = messagesMapper.selectMaps(messagesQueryWrapper);
        Messages message = messagesMapper.selectOne(messagesQueryWrapper);
        HashMap<String, Object> resHashMap = new HashMap<>();
        resHashMap.put("mailDetail", message);
        if (message == null) {
            return new ResultVo(ResultCode.NOT_FOUND);
        }
        return new ResultVo(resHashMap);
    }

    @Override
    public ResultVo updateMessageIsRead(ShopMessageUpdateIsReadVd shopMessageUpdateIsReadVd) {
        try {

            Integer isRead = shopMessageUpdateIsReadVd.getIsRead();
            Integer messageId = shopMessageUpdateIsReadVd.getId();
            String token = shopMessageUpdateIsReadVd.getToken();
            TokenVo tokenVo = TokenUtils.tokenToObject(token);
            int shopId = tokenVo.getShopId();
            QueryWrapper<Messages> messagesQueryWrapper = new QueryWrapper<>();
            messagesQueryWrapper.eq("id", messageId);
            Messages message = messagesMapper.selectOne(messagesQueryWrapper);
            if (message == null) {
                return new ResultVo(ResultCode.NOT_FOUND);
            }
            Integer shopIdDb = message.getShopId();
            if (shopIdDb != shopId) {
                return new ResultVo(ResultCode.AUTH_ERROR);
            }
            message.setIsRead(isRead);
            int effected = messagesMapper.updateById(message);
            if (effected == 0) {
                return new ResultVo(ResultCode.UPDATE_ERROR);
            }
            return new ResultVo("更新成功");
        } catch (Exception e) {
            return new ResultVo(e.getMessage());
        }
    }

    @Override
    public List getMessages(Integer userId) {
        return messagesMapper.getMessages(userId);
    }

}
