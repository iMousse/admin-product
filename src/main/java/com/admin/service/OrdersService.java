package com.admin.service;

import com.admin.domain.Member;
import com.admin.domain.Orders;


import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-21
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public interface OrdersService {


    /**
     * 销售生成订单
     *
     * @param orders
     * @param member
     * @return
     */
    int add(Orders orders, Member member);


    /**
     * 销售或产品经理查询没有接单的订单
     *
     * @return
     * @param page
     * @param size
     */
    List<Orders> sendOrders(Integer page, Integer size);

    /**
     * 查询产品经理是否有订单
     *
     * @return
     */
    Orders myselfOrders(String username);

    /**
     * 销售派单
     * @param username
     * @param orderId
     * @return
     */
    int updateOrderUsername(String username, Long orderId);


    /**
     * 如果产品经理没有订单，则自己接单
     *
     * @param page
     * @param size
     * @return
     */
    List<Orders> manager(Integer page, Integer size);


    /**
     * 管理员审核订单
     *
     * @return
     * @param page
     * @param size
     */
    List<Orders> getOrdersDone(Integer page, Integer size);

    /**
     * 查询订单详情
     *
     * @param orderId
     * @return
     */
    Orders getById(Long orderId);

    /**
     * 产品经理接单
     * @param username
     * @param orderId
     * @return
     */
    int putUsername(String username, Long orderId);

    /**
     *
     * @param orderId
     * @return
     */
    int putOrderStatusDone(Long orderId);

    /**
     * 订单审核过程
     *
     * @param orders
     * @return
     */
    int auditManger(Orders orders);


}
