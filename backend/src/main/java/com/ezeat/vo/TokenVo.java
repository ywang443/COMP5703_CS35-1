package com.ezeat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * token对象
 */
public class TokenVo {
    private long timestamp; // 时间戳

    private int userId; // 用户id

    private int shopId; // 店铺id

    private String sign; // 密文
}
