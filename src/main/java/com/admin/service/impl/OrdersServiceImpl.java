package com.admin.service.impl;


import com.admin.constant.Constant;
import com.admin.domain.Member;
import com.admin.domain.Orders;
import com.admin.domain.Product;
import com.admin.mapper.MemberMapper;
import com.admin.mapper.OrdersMapper;
import com.admin.mapper.ProductMapper;
import com.admin.service.OrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-21
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private MemberMapper memberMapper;


    @Override
    public List<Orders> manager(Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return ordersMapper.selectByUsernameNotNull();
    }

    @Override
    public List<Orders> getOrdersDone(Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return ordersMapper.selectByOrdersStatus();
    }

    @Override
    public Orders getById(Long orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int putUsername(String username, Long orderId) {
        return ordersMapper.updateUsername(username,orderId);
    }

    @Override
    public int putOrderStatusDone(Long orderId) {

        return ordersMapper.updateOrderStatus(orderId);
    }

    @Override
    public int auditManger(Orders orders) {
        //审核不通过
        if (orders.getOrderAudit() == 0) {
            ordersMapper.updateOrderAuditNoPass(orders);
        }

        return ordersMapper.updateOrderAuditPass(orders);
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public int add(Orders orders, Member member) {
        //设置订单的价格
        Product product = productMapper.selectByPrimaryKey(orders.getOrderProductId());
        BigDecimal productPrice = product.getProductPrice();
        BigDecimal price = productPrice.multiply(new BigDecimal(orders.getOrderProductNum()));
        orders.setOrderPrice(price);


        //设置订单为开启
        orders.setOrderStatus(Constant.OrderStatus.ORDERS_OPEN);

        String orderNumber = this.getOrderNumber();
        orders.setOrderNum(orderNumber);


        //查询会员是否存在数据库中
        Member memberDB = memberMapper.selectByNameAndPhone(member.getMemberName(), member.getMemberPhone());

        //如果不再数据库中则直接插入
        if (memberDB == null) {
            memberMapper.insert(member);
            //设置订单甲方名字
            orders.setOrderMemberId(member.getMemberId());
        }

        //如果存在数据库中，那就将数据库中的会员id插入订单
        if (memberDB != null) {
            orders.setOrderMemberId(memberDB.getMemberId());
        }

        orders.setOrderCreateTime(new Date());
        
        return ordersMapper.insertSelective(orders);
    }

    @Override
    public List<Orders> sendOrders(Integer page, Integer size) {
        
        PageHelper.startPage(page, size);

        return ordersMapper.selectNoUsername();
    }

    @Override
    public Orders myselfOrders(String username) {

        return ordersMapper.selectByUsernameAndStatus(username);
    }

    @Override
    public int updateOrderUsername(String username, Long orderId) {
        return ordersMapper.updateUsername(username, orderId);
    }


    public String getOrderNumber() {
        //生成订单号
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();


        for (int i = 0; i < 3; i++) {
            result.append(random.nextInt(10));
        }

        return newDate + result;
    }
}
