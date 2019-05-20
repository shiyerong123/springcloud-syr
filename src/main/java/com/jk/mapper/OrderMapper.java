package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderMapper {

    Integer findOrderCount(@Param("order") Order order);

    List<Order> findOrder(@Param("start") Integer start,@Param("limit") Integer limit,@Param("order") Order order);
    @Select("select * from t_status")
    List<Status> findStatus();
    @Select("select * ,href hrefURL  from t_nav")
    List<MenuTree> getTreeAll();
    @Select("SELECT SUM(number* singleton) from t_goods where orderNo = #{orderNo}")
    Integer weight(String orderNo);
    @Select("SELECT SUM(number* volume) from t_goods where orderNo = #{orderNo}")
    Integer volume(String orderNo);
    @Select("select * from t_wetherorder")
    List<WetherOrder> findWether();
    @Select("select * from t_area where pid=0")
    List<liandong> getsheng();

    @Select("select * from t_area where pid=#{typeid}")
    List<liandong> getshi(@Param("typeid") Integer typeid);

    @Select("select * from t_area where pid=#{typeid}")
    List<liandong> getxian(@Param("typeid")Integer typeid);
    @Select("select name,id from t_area where id = #{hairProvinces}")
    String queryprvince(String hairProvinces);
    @Select("select name,id from t_area where id = #{closedProvinces}")
    String city(String closedProvinces);
    @Select("select name,id from t_area where id = #{city}")
    String queryprvince1(String city);
    @Select("select name,id from t_area where id = #{city2}")
    String city1(String city2);

    Integer findDealCount(@Param("deal") Deal deal);

    List<Deal> findDeal(@Param("start") Integer start,@Param("limit") Integer limit,@Param("deal") Deal deal);
    Particulars findById(String orderNo);
    @Select("select * from t_goods where orderNo=#{goodsId}")
    List<Good> findgoods(Integer goodsId);
    @Select("select count(number) from t_goods where orderNo=#{goodsId}")
    Integer number(String orderNo);
    @Select("select estimateMoney-serviceMoney from t_deal where dealNo=#{dealNo}")
    Double findsumMoney(String dealNo);
    @Select("select name,id from t_area where id = #{hairProvinces}")
    String queryprvince2(String hairProvinces);
    @Select("select name,id from t_area where id = #{closedProvinces}")
    String city2(String closedProvinces);
    @Select("select name,id from t_area where id = #{city}")
    String queryprvince3(String city);
    @Select("select name,id from t_area where id = #{city2}")
    String city4(String city2);
}
