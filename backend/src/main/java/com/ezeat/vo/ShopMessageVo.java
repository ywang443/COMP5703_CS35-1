package com.ezeat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopMessageVo {
    private Integer id;
    private Integer shopId;
    private Integer userId;
    private Integer status;
    private Integer type;
    private String title;
    private String content;
    private String img;
    private String createdAt;
}
