package com.jk.controller;


import com.jk.model.*;
import com.jk.service.OrderService;

import com.jk.user.TreeNoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import com.jk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class OrderController {
   @Autowired
    OrderService orderService;


    /**
     * 左侧导航树
     * @return
     */
    @RequestMapping("getTreeAll")
    @ResponseBody
    public List<MenuTree> getTreeAll(){

        List<MenuTree> list = orderService.getTreeAll();

        list =  TreeNoteUtil.getFatherNode(list);

        return list;


}


    /**
     * 公共跳转页面的方法
     * @param url
     * @return
     */

    @RequestMapping("quanbu")
    public  String quanbu(String url){
        return url;
    }

    /**
     * 查询
     * @param page
     * @param limit
     * @param order
     * @return
     */
    @RequestMapping("findOrder")
    @ResponseBody
    public HashMap<String,Object> findOrder(Integer page, Integer limit, Order order){
        return orderService.findOrder(page,limit,order);
    }

    /**
     * 查询支付状态
     * @return
     */
    @RequestMapping("findStatus")
    @ResponseBody
    public List<Status> findStatus(){
        return orderService.findStatus();
    }
    /**
     * 查询是否上门提货
     * @return
     */
    @RequestMapping("findWether")
    @ResponseBody
    public List<WetherOrder> findWether(){
        return orderService.findWether();
    }

    //三级联动 省
    @RequestMapping("getsheng")
    @ResponseBody
    public List<liandong> getsheng() {
        List<liandong> getsheng = orderService.getsheng();
        return getsheng;
    }

    //三级联动 市
    @RequestMapping("getshi")
    @ResponseBody
    public List<liandong> getshi(Integer typeid) {
        List<liandong> getshi = orderService.getshi(typeid);
        return getshi;
    }

    @RequestMapping("getxian")
    @ResponseBody
    public List<liandong> getxian(Integer typeid) {
        List<liandong> getshi = orderService.getxian(typeid);
        return getshi;
    }

    /**
     * 查询交易
     * @param page
     * @param limit
     * @param deal
     * @return
     */
    @RequestMapping("findDeal")
    @ResponseBody
    public HashMap<String,Object> findDeal(Integer page, Integer limit, Deal deal){
        return orderService.findDeal(page,limit,deal);
    }

    //ID查询
    @RequestMapping("/findById")
    @ResponseBody
    public Particulars  findById(String orderNo){

        Particulars particulars = orderService.findById(orderNo);

        return  particulars;
    }
    @RequestMapping("/findgoods")
    @ResponseBody
    public List<Good> findgoods(Integer goodsId){

        List<Good> good = orderService.findgoods(goodsId);
        return good;
    }
}
