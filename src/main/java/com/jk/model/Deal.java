package com.jk.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Deal {
    private Integer id;//主键
    private String dealNo;//交易号
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date dealTime;//交易时间
    private Double dealMoney;//交易金额
    private Integer dealType;//交易类型      1运费 2手续费 3提现
    private Integer expensesType;//收支类型     1全部 2收入 3支出
    private Integer orderId;//关联订单号id
    private String cardNo;//银行卡号
    private Double serviceMoney;//服务费
    private Double estimateMoney;//预估费用
    private Integer settlement;//结算状态
    private Double depositMoney;//提现余额
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date applyTime;//申请时间
    private Integer applyStatus;//审核状态
    private Integer commpanyId;//关联公司id


    private String orderNo;//订单号
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date placeDate;//下单时间

    //临时字段
    private Double sumMoney;//结算金额
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;//开始交易时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;//结束交易时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startApplyTime;//开始申请时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endApplyTime;//结束申请时间
}