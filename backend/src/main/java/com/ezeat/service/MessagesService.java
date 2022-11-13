package com.ezeat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ezeat.entity.Messages;
import com.ezeat.validator.CategoryVd;
import com.ezeat.validator.MessageDetailVd;
import com.ezeat.validator.MessageListVd;
import com.ezeat.validator.ShopMessageUpdateIsReadVd;
import com.ezeat.vo.ResultVo;

import java.util.List;

public interface MessagesService extends IService<Messages> {
    List getAll();

    ResultVo getUserMessagesByType(MessageListVd messageListVd);

    ResultVo getUserMessageDetail(MessageDetailVd messageDetailVd);

    ResultVo updateMessageIsRead(ShopMessageUpdateIsReadVd shopMessageUpdateIsReadVd);

    List getMessages(Integer userId);
}
