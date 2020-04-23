package com.admin.service;


import com.admin.domain.Member;
import com.admin.domain.Orders;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-24
 * Time: 23:30
 * To change this template use File | Settings | File Templates.
 */
public interface MemberService {

    Member login(String memberName, String memberPhone);


    List<Orders> findAll(Long memberId, Integer page, Integer size);


    Orders getOrderById(Long orderId);


    int audit(Orders orders);


}
