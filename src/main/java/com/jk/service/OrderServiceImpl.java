package com.jk.service;

import com.alibaba.fastjson.JSONObject;
import com.jk.mapper.OrderMapper;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jk.mapper.OrderMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;


    @Override
    public HashMap<String, Object> findOrder(Integer page, Integer limit, Order order,Integer carrid) {
       HashMap<String, Object> hashMap = new HashMap<>();
        Integer count = orderMapper.findOrderCount(order,carrid);
        Integer start = (page-1)*limit;
        List<Order> list = orderMapper.findOrder(start,limit,order,carrid);
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);


        for (int i=0;i<list.size();i++){

            Integer  zhong = orderMapper.weight(list.get(i).getOrderNo());
            Integer  volume = orderMapper.volume(list.get(i).getOrderNo());
            list.get(i).setZhong(zhong);
            list.get(i).setVolume(volume);
        }

        for (int i=0;i<list.size();i++){
            String  province = orderMapper.queryprvince(list.get(i).getHairProvinces());
            String   province1 = orderMapper.city(list.get(i).getClosedProvinces());
            String  county = orderMapper.queryprvince1(list.get(i).getCity());
            String  county1 = orderMapper.city1(list.get(i).getCity2());
            String province3 =province+county;
            String  province4 =province1+county1;
            list.get(i).setCounty(province3);
            list.get(i).setCounty1(province4);
        }

        return hashMap;
    }

    @Override
    public List<Status> findStatus() {
        return orderMapper.findStatus();
    }

    @Override
    public List<MenuTree> getTreeAll() {
        List<MenuTree> list = orderMapper.getTreeAll();
        return list;
    }

    @Override
    public List<WetherOrder> findWether() {
        return orderMapper.findWether();
    }

    @Override
    public List<liandong> getsheng() {
        List<liandong> getsheng = orderMapper.getsheng();
        return getsheng;
    }

    @Override
    public List<liandong> getshi(Integer typeid) {
        List<liandong> getshi = orderMapper.getshi(typeid);
        return getshi;
    }

    @Override
    public List<liandong> getxian(Integer typeid) {
        List<liandong> getshi = orderMapper.getxian(typeid);
        return getshi;
    }

    @Override
    public HashMap<String, Object> findDeal(Integer page, Integer limit, Deal deal,Integer carrid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Integer count = orderMapper.findDealCount(deal,carrid);
        Integer start = (page-1)*limit;
        List<Deal> list = orderMapper.findDeal(start,limit,deal,carrid);
        for (int i=0;i<list.size();i++){
            Double sumMoney1 = orderMapper.findsumMoney(list.get(i).getDealNo());
            list.get(i).setSumMoney(sumMoney1);
        }
        hashMap.put("count",count);
        hashMap.put("data",list);
        hashMap.put("code",0);
       // hashMap.put("sumMoney",sumMoney);
        return hashMap;
    }


    @Override
    public Particulars findById(String orderNo) {
        Particulars particulars = orderMapper.findById(orderNo);

            String  province1 = orderMapper.queryprvince2(particulars.getHairProvinces());
            String   province2 = orderMapper.city2(particulars.getClosedProvinces());
            String  county3 = orderMapper.queryprvince3(particulars.getCity());
            String  county4 = orderMapper.city4(particulars.getCity2());
            String province5 =province1+county3;
            String  province6 =province2+county4;
             particulars.setCounty(province5);
             particulars.setCounty1(province6);


        return particulars;
    }

    @Override
    public List<Good> findgoods(String orderNo) {

        List<Good> map = orderMapper.findgoods(orderNo);
        for (Integer i=0;i<map.size();i++){
            Integer  zhong = orderMapper.weight(map.get(i).getOrderNo());
            Integer  volume = orderMapper.volume(map.get(i).getOrderNo());
            Integer  numbers = orderMapper.number(map.get(i).getOrderNo());
            map.get(i).setSingletons(zhong);
            map.get(i).setVolumes(volume);
            map.get(i).setNumbers(numbers);
        }
        return map;
    }
    @Override
    public void updateAll(String id) {
        orderMapper.updateAll(id);
    }

    @Override
    public Integer findstay() {
        Integer  a =   orderMapper.findstay();
        return a;
    }

    @Override
    public String rental() {
        String  a =   orderMapper.rental();
        return a;
    }

    @Override
    public void updateAll1(String id) {
        orderMapper.updateAll1(id);
    }

    @Override
    public Integer findstay1() {
        Integer  a =   orderMapper.findstay1();
        return a;
    }

    @Override
    public void updateAll2(String id) {
        orderMapper.updateAll2(id);
    }
    @Override
    public Integer findstay2() {
        Integer  a =   orderMapper.findstay2();
        return a;
    }

    @Override
    public Commpany findgong() {
        Commpany  commpany = orderMapper.findgong();
        return commpany;
    }

    @Override
    public Integer newest() {

        return orderMapper.newest();
    }


    @Override
    public Commpany  findCommpany(Integer carrid) {
        Commpany commpany  = orderMapper.findCommpany(carrid);
        return commpany;
    }

    @Override
    public void updateCommpany(Commpany commpany) {
        orderMapper.updateCommpany(commpany);
    }

}
