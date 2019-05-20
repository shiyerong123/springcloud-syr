package com.jk.service;

import com.jk.model.*;

import java.util.HashMap;
import java.util.List;


import java.util.List;

public interface OrderService {
    HashMap<String,Object> findOrder(Integer page, Integer limit, Order order);

    List<Status> findStatus();
    List<MenuTree> getTreeAll();

    List<WetherOrder> findWether();

    List<liandong> getsheng();

    List<liandong> getshi(Integer typeid);

    List<liandong> getxian(Integer typeid);

    HashMap<String, Object> findDeal(Integer page, Integer limit, Deal deal);


    Particulars findById(String orderNo);

    List<Good> findgoods(Integer goodsId);


}
