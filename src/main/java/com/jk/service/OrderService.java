package com.jk.service;

import com.jk.model.*;

import java.util.HashMap;
import java.util.List;


import java.util.List;

public interface OrderService {
    HashMap<String,Object> findOrder(Integer page, Integer limit, Order order,Integer carrid);

    List<Status> findStatus();
    List<MenuTree> getTreeAll();

    List<WetherOrder> findWether();

    List<liandong> getsheng();

    List<liandong> getshi(Integer typeid);

    List<liandong> getxian(Integer typeid);

    HashMap<String, Object> findDeal(Integer page, Integer limit, Deal deal,Integer carrid);


    Particulars findById(String orderNo);

    List<Good> findgoods(String orderNo);

    void updateAll(String id);

    Integer findstay();

    String rental();

    void updateAll1(String id);
    Integer findstay1();

    void updateAll2(String id);

    Integer findstay2();

    Commpany findgong();

    Integer newest();

    Commpany findCommpany(Integer carrid);

    void updateCommpany(Commpany commpany);
}
