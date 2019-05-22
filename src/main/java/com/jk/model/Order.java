package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
public class Order {
    private Integer id;//主键
    private String orderNo;//订单号
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date placeDate;//下单时间
    private String closedProvinces;//收货地址
    private String hairProvinces;//发货地址
    private String forwarding;//发货单位
    private String consignee;//收货单位
    private String shipping;//发货联系人
    private String delivery;//收货联系人
    private String sendPhone;//发货人手机号
    private String deliverPhone;//收货人地址
    private String bringSite;//提货地址
    private String presentSite;//送货地址
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date affirmDate;//结算时间
    private Integer estimate;//预估运费
    private  Integer aaa;//判断收发货地址
    private   String  city2;     //到市
    private   String  city;      //从市


    private   String  county;    //从市
    private   String  county1;    //到市


    private Integer statusId;//关联支付状态id
    private String name;//支付类型
    private Integer wetherOrderId;//关联是否上门提货
    private String wethName;//是否上门提货类型
    private Integer goodsId;//关联货物的id
    private  Integer zhong;//总重量
    private Integer volume;//总体积
    private Integer draw;
    private String  fleid;
    private Integer commpanyId;
    private Integer zt;//自定义字段


    //临时字段
    private Double estimateMoney;//预估费用
    private Integer shiid;//市
    private Integer xianid;//县
    private Integer slid;//省
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startPlaceDate;//开始下单时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endPlaceDate;//结束下单时间
    private Integer accept;//受理状态



}
