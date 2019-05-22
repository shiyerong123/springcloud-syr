package com.jk.controller;


import com.alibaba.fastjson.JSON;
import com.jk.model.*;
import com.jk.rmi.ThisClient;
import com.jk.service.OrderService;

import com.jk.user.TreeNoteUtil;
import com.jk.util.AliyunOSSUtil;
import com.jk.util.CheckImgUtil;
import com.jk.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class OrderController {
   @Autowired
    OrderService orderService;

    @Autowired
    private ThisClient thisClient;


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
    public HashMap<String,Object> findOrder(Integer page, Integer limit, Order order,HttpSession session){
        Integer carrid =(Integer)session.getAttribute("carrid");
        return orderService.findOrder(page,limit,order,carrid);
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
    public HashMap<String,Object> findDeal(Integer page, Integer limit, Deal deal,HttpSession session){
        Integer carrid =(Integer)session.getAttribute("carrid");
        return orderService.findDeal(page,limit,deal,carrid);
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
    public List<Good> findgoods(String orderNo){
        List<Good> good = orderService.findgoods(orderNo);
        return good;
    }

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    @RequestMapping("/updateAll")
    @ResponseBody
    public String updateAll(String id){
        orderService.updateAll(id);
        return null;
    }

    /**
     * 查询待审核条数
     * @return
     */
    @RequestMapping("/findstay")
    @ResponseBody
    public Integer findstay(){
        Integer a =  orderService.findstay();
        return a;
    }

    /**
     * 预估运费总额
     * @return
     */
    @RequestMapping("/rental")
    @ResponseBody
    public String rental(){
        String a =  orderService.rental();
        return a;
    }
    //用户登录
    @RequestMapping("login")
    @ResponseBody
    public HashMap<String,Object> login(HttpServletResponse response, String username, String password, Integer remPwd , HttpSession session, Model model){
        HashMap<String,Object>  map= thisClient.findUser(username,password,2);
        if ((Integer) map.get("status")==0){
            String ur = map.get("usr").toString();
            Carrier usr = JSON.parseObject(ur,Carrier.class);
            session.setAttribute("carrid",usr.getId());
            if (usr!=null){
                session.setAttribute("username",usr.getCompanyName());
                if (remPwd!=null){
                    Cookie cookie = new Cookie(Constant.cookieNamePwd,username+Constant.splitstr+password);
                    cookie.setMaxAge(604800);
                    response.addCookie(cookie);
                    model.addAttribute("password",1);
                }else{
                    Cookie cookie = new Cookie(Constant.cookieNamePwd,"");
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }else{
                Cookie cookie = new Cookie(Constant.cookieNamePwd,"");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        return map;
    }

    //跳转登陆页面
    @RequestMapping("/tologin")
    public String tologin(HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals(Constant.cookieNamePwd)){
                    String value = cookie.getValue();
                    String[] split = value.split(Constant.splitstr);
                    model.addAttribute("username",split[0]);
                    model.addAttribute("password",split[1]);
                }
            }
        }
        return "CarrLogin";
    }
    //邮箱登录
    @RequestMapping("emailLogin")
    @ResponseBody
    public HashMap<String,Object> emailLogin(String email,String emailCode,HttpSession session){
        HashMap<String,Object> map= thisClient.emailLogin(email,emailCode,2);
        if ((Integer)map.get("status")==0){
            Carrier user = (Carrier)session.getAttribute("usr");
            session.setAttribute("carrid",user.getId());
            session.setAttribute("user",user);
        }
        return map;
    }

    //手机号登录
    @RequestMapping("numlogin")
    @ResponseBody
    public HashMap<String,Object> numlogin(String phone,String smsCode,HttpSession session){
        HashMap<String,Object> map= thisClient.numlogin(phone,smsCode,2);
        if ((Integer)map.get("status")==0){
            Carrier user = (Carrier)session.getAttribute("usr");
            session.setAttribute("carrid",user.getId());
            session.setAttribute("user",user);
        }
        return map;
    }


    //发送手机验证码
    @RequestMapping(value="findSmsCode",produces="application/json;charset=utf-8")
    @ResponseBody
    public HashMap<String,Object> findSmsCode(String loginNumber,HttpSession session){
        HashMap<String, Object> hashMap = thisClient.findDxCode(loginNumber,2);
        if ((Integer) hashMap.get("status")==0){
            String  user = hashMap.get("user").toString();
            session.setAttribute("usr",JSON.parseObject(user,Carrier.class));
        }

        return hashMap;
    }

    //发送QQ邮箱验证码
    @RequestMapping("getCheckCode")
    @ResponseBody
    public HashMap<String,Object> getCheckCode(String email, HttpSession session){
        HashMap<String,Object> map= thisClient.getCheckCode(email,2);
        if ((Integer)map.get("status")==0){
            session.setAttribute("usr",JSON.parseObject(map.get("user").toString(),Carrier.class));
        }
        return map;
    }
    @RequestMapping("getSession")
    @ResponseBody
    public String getSession(String ssionKey,HttpSession session){
        return   session.getAttribute(ssionKey).toString();
    }
    @RequestMapping("getImgCode")
    public  void getImgCode(HttpServletRequest request , HttpServletResponse response) throws Exception{
        CheckImgUtil.checkImg(request, response);
        String str = (String) request.getSession().getAttribute("checkcode");
        System.out.println(str);
    }
    //修改状态
    @RequestMapping("/updateAll1")
    @ResponseBody
    public String updateAll1(String id){
        orderService.updateAll1(id);
        return null;
    }
    //修改状态
    @RequestMapping("/updateAll2")
    @ResponseBody
    public String updateAll2(String id){
        orderService.updateAll2(id);
        return null;
    }

    @RequestMapping("/findstay1")
    @ResponseBody
    public Integer findstay1(){
        Integer a =  orderService.findstay1();
        return a;
    }

    @RequestMapping("/findstay2")
    @ResponseBody
    public Integer findstay2(){
        Integer a =  orderService.findstay2();
        return a;
    }

    /**
     * 查询
     * @return
     */
    @RequestMapping("findgong")
    @ResponseBody
    public Commpany findgong(){
        Commpany commpany =  orderService.findgong();
        return  commpany;
    }
    @RequestMapping("newest")
    @ResponseBody
    public  Integer  newest(){
        Integer newest=orderService.newest();
        return  newest;
    }

    @RequestMapping("findCommpany")
    @ResponseBody
    public Commpany findCommpany(HttpSession session){
        Integer carrid =(Integer)session.getAttribute("carrid");
        Commpany commpany = orderService.findCommpany(carrid);
        return commpany;
    }

    /**
     * 上传图片
     * @param companyLogo
     * @return
     */
    @RequestMapping("imgUpload")
    @ResponseBody
    public  HashMap<String,Object> imgUpload( MultipartFile companyLogo){

        String filename = companyLogo.getOriginalFilename();
        System.out.println(filename);
        HashMap<String, Object> map = new HashMap<>();
        try {

            if (companyLogo != null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(companyLogo.getBytes());
                    os.close();
                    companyLogo.transferTo(newFile);
                    // 上传到OSS
                    String uploadUrl = AliyunOSSUtil.upLoad(newFile);

                    map.put("imgId",uploadUrl);
                    return  map;
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        map.put("imgId","");
        return  map;
    }

    /**
     * 修改公司资料
     * @param commpany
     */
    @RequestMapping("updateCommpany")
    @ResponseBody
    public void updateCommpany(Commpany commpany){
         orderService.updateCommpany(commpany);
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "CarrLogin";
    }
}
