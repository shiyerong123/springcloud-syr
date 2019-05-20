package com.jk.model;

import lombok.Data;

@Data
public class Good {
    private Integer id;//主键
    private String goodsName;//货物名称
    private Integer number;//件数
    private String packaging;//包装类型
    private Integer singleton;//d单件重量
    private Integer volume;//单件体积
    private String photograph;//图片
    private String explain;//发货说明
    private Integer freightId;//关联钱的id

    private  String  orderNo;

    private Integer singletons;//总重量
    private Integer volumes;//总体积
    private Integer numbers;//总件数
}
