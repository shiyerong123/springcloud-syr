package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderMapper {

    Integer findOrderCount(@Param("order") Order order,@Param("carrid") Integer carrid);

    List<Order> findOrder(@Param("start") Integer start,@Param("limit") Integer limit,@Param("order") Order order,@Param("carrid") Integer carrid);
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

    Integer findDealCount(@Param("deal") Deal deal,@Param("carrid") Integer carrid);

    List<Deal> findDeal(@Param("start") Integer start,@Param("limit") Integer limit,@Param("deal") Deal deal,@Param("carrid") Integer carrid);
    Particulars findById(String orderNo);
    @Select("select * from t_goods where orderNo=#{orderNo}")
    List<Good> findgoods(String orderNo);
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
    @Update("update t_order set accept=2 where orderNo = #{id} ")
    void updateAll(String id);
    @Select("select count(orderNo) from t_order where accept=1")
    Integer findstay();
    @Select(" select SUM(d.estimateMoney) from t_order o" +
            "   left join t_status s on o.statusId = s.id" +
            "   left join t_deal d on o.id=d.orderId" +
            "   left join t_wetherorder t on o.wetherOrderId=t.id" +
            "   left join t_accept a on o.accept= a.id where o.accept=1")
    String rental();
    @Update("update t_order set accept=3 where orderNo = #{id} ")
    void updateAll1(String id);
    @Select("select count(orderNo) from t_order where accept=2")
    Integer findstay1();
    @Update("update t_order set accept=4 where orderNo = #{id} ")
    void updateAll2(String id);
    @Select("select count(orderNo) from t_order where accept=4")
    Integer findstay2();
    @Select("select* from t_Commpany where id=#{carrid}")
   Commpany findCommpany(Integer carrid);
    @Select("select * from t_commpany where id=1")
    Commpany findgong();
    @Select("select count(orderNo) from t_order where  placeDate >= now()-interval 3 day")
    Integer newest();

   @Update("update from t_commpany set companyName=#{companyName},companyInfo=#{companyInfo},companyPhone=#{companyPhone},relationName=#{relationName},station=#{station},companyLogo=#{companyLogo},serviceAim=#{serviceAim},introduction=#{introduction},industry=#{industry}")
    void updateCommpany(Commpany commpany);
}
