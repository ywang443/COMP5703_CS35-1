package com.ezeat.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCollectionVo {
    private int cid;
    private int id;
    private String name;
    private String img;
    private String phone;
    private String address;
    private String average;
    private String createdAt;
}
