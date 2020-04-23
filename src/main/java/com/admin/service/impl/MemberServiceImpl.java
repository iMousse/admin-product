package com.admin.service.impl;

import com.admin.domain.Member;
import com.admin.domain.Orders;
import com.admin.mapper.MemberMapper;
import com.admin.mapper.OrdersMapper;
import com.admin.service.MemberService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-24
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public Member login(String memberName, String memberPhone) {
        return memberMapper.selectByNameAndPhone(memberName, memberPhone);
    }

    @Override
    public List<Orders> findAll(Long memberId, Integer page, Integer size) {

        PageHelper.startPage(page, size);

        return ordersMapper.selectByMemberPrimaryKey(memberId);
    }

    @Override
    public Orders getOrderById(Long orderId) {
        return ordersMapper.selectByPrimaryKey(orderId);
    }

    @Override
    public int audit(Orders orders) {

        if (orders.getOrderPay() == 0) {

            ordersMapper.updateOrderPayNoPass(orders);
        }

        return ordersMapper.updateOrderPayPass(orders);
    }
}
