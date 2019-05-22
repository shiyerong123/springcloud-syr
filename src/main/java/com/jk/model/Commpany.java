package com.jk.model;

import lombok.Data;

@Data
public class Commpany {
    private Integer id;//主键
    private String companyName;//公司名称
    private String companyInfo;//公司简称
    private String companyPhone;//接单电话
    private String relationName;//联系人
    private String station;//官方网站
    private String companyLogo;//公司标志
    private String serviceAim;//服务宗旨
    private String introduction;//公司简介
    private String industry;//优势服务行业
    private String account;//账号
    private String password;//密码
    private String email;//邮箱
    private String companyProvince;//公司地址
    private String grade;

}
