package com.admin.controller;


import com.admin.domain.Member;
import com.admin.domain.Orders;
import com.admin.domain.User;
import com.admin.service.OrdersService;
import com.admin.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-21
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private UserService userService;


    //新建订单
    @RequestMapping("/save.do")
    public String save(Orders orders, Member member, Long productId) {

        orders.setOrderProductId(productId);

        ordersService.add(orders, member);

        return "redirect:/product/findAll.do";
    }

    //销售
    @RequestMapping("/send.do")
    public ModelAndView send(@RequestParam(name = "page", defaultValue = "1") Integer page,
                             @RequestParam(name = "size", defaultValue = "4") Integer size) {

        List<Orders> orders = ordersService.sendOrders(page, size);

        ModelAndView mv = new ModelAndView();

        PageInfo<Orders> pageInfo = new PageInfo<>(orders);

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-send");

        return mv;
    }


    //查询订单
    @RequestMapping("/findById.do")
    public ModelAndView findById(Long orderId) {

        Orders orders = ordersService.getById(orderId);

        ModelAndView mv = new ModelAndView();

        mv.addObject("orders", orders);
        mv.setViewName("orders-show");

        return mv;
    }

    //派单
    @RequestMapping("/sendOrders.do")
    public ModelAndView sendOrders(Long orderId) {

        ModelAndView mv = new ModelAndView();

        List<User> userInfos = userService.findNoOrdersUsername();

        mv.addObject("users", userInfos);
        mv.addObject("orderId", orderId);

        mv.setViewName("orders-send-pick");


        return mv;
    }

    //派单成功
    @RequestMapping("/pick.do")
    public String pick(Long orderId, String username) {

        ordersService.updateOrderUsername(username, orderId);

        return "redirect:send.do";
    }


    //产品经理
    @RequestMapping("/manager.do")
    public ModelAndView findAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                @RequestParam(name = "size", defaultValue = "4") Integer size,
                                HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        //获取UserInfo
        User user = (User) request.getSession().getAttribute("user");

        //查询经理是否有正在进行的订单
        Orders orders = ordersService.myselfOrders(user.getUsername());

        if (orders == null) {
            //如果订单为空可以接单
            List<Orders> ordersList = ordersService.sendOrders(page, size);

            PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);

            mv.addObject("pageInfo", pageInfo);
            mv.setViewName("orders-manager");
        } else {

            //不为空则继续完成
            mv.addObject("orders", orders);
            mv.setViewName("orders-done");
        }


        return mv;
    }

    //产品经理接单
    @RequestMapping("/putOrders.do")
    public ModelAndView putOrders(Long orderId, HttpServletRequest request) {

        ModelAndView mv = new ModelAndView();

        //获取UserInfo
        User user = (User) request.getSession().getAttribute("user");

        ordersService.putUsername(user.getUsername(), orderId);

        Orders orders = ordersService.getById(orderId);

        mv.addObject("orders", orders);

        mv.setViewName("orders-done");

        return mv;
    }

    //产品经理完成订单
    @RequestMapping("/putDone.do")
    public ModelAndView putDone(Long orderId) {
        ModelAndView mv = new ModelAndView();

        ordersService.putOrderStatusDone(orderId);

        Orders orders = ordersService.getById(orderId);

        mv.addObject("orders", orders);
        mv.setViewName("orders-done");

        return mv;
    }


    //管理员
    @RequestMapping("/audit.do")
    public ModelAndView audit(@RequestParam(name = "page", defaultValue = "1") Integer page,
                              @RequestParam(name = "size", defaultValue = "4") Integer size) {

        List<Orders> orders = ordersService.getOrdersDone(page, size);


        ModelAndView mv = new ModelAndView();

        PageInfo<Orders> pageInfo = new PageInfo<>(orders);


        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-audit");

        return mv;
    }

    //管理员进入审核页面
    @RequestMapping("/auditLoad.do")
    public ModelAndView auditLoad(Long orderId) {

        ModelAndView mv = new ModelAndView();

        Orders orders = ordersService.getById(orderId);

        mv.addObject("orders", orders);

        mv.setViewName("orders-audit-load");

        return mv;
    }

    //管理员审核逻辑
    @RequestMapping("/auditManger.do")
    public String auditManger(Orders orders) {

        ordersService.auditManger(orders);

        return "redirect:audit.do";

    }
    


}
