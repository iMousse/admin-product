package com.admin.mapper;

import com.admin.domain.Member;
import com.admin.domain.Orders;

import com.admin.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-21
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public interface OrdersMapper {

    @Select("select * from orders")
    List<Orders> selectAll();

    @Insert("insert into orders (orderNum,orderStartTime,orderDesc,orderStatus,orderProductId,orderProductNum,orderPrice,orderMemberId,orderCreateTime) values(#{orderNum},#{orderStartTime},#{orderDesc},#{orderStatus},#{orderProductId},#{orderProductNum},#{orderPrice},#{orderMemberId},#{orderCreateTime}) ")
    int insertSelective(Orders orders);

    @Select("select * from orders where orderUsername is null")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey"))
    })
    List<Orders> selectNoUsername();

    //有产品经理的订单并且还在支付完成之前那就不能继续接单
    @Select("select * from orders where orderUsername = #{username} and (orderPay is null or orderPay = 0)")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey")),
            @Result(column = "orderMemberId", property = "member", javaType = Member.class, one = @One(select = "com.admin.mapper.MemberMapper.selectByPrimaryKey"))
    })
    Orders selectByUsernameAndStatus(String username);


    @Select("select * from orders where orderUsername is not null")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey"))
    })
    List<Orders> selectByUsernameNotNull();


    @Select("select * from orders where orderStatus = 1 and (orderAudit = 0 or orderAudit is  null) and (orderPay = 0 OR orderPay IS NULL)")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey"))
    })
    List<Orders> selectByOrdersStatus();


    @Select("select * from orders where orderId = #{orderId} ")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey")),
            @Result(column = "orderMemberId", property = "member", javaType = Member.class, one = @One(select = "com.admin.mapper.MemberMapper.selectByPrimaryKey"))
    })
    Orders selectByPrimaryKey(Long orderId);

    @Update("update orders set orderUsername = #{username} where orderId = #{orderId}")
    int updateUsername(@Param("username") String username, @Param("orderId") Long orderId);

    @Update("update orders set orderStatus = 1 ,orderAudit = null where orderId = #{orderId}")
    int updateOrderStatus(Long orderId);


    @Update("update orders set orderAudit = #{orderAudit}, orderReport = #{orderReport} where orderId = #{orderId}")
    int updateOrderAuditPass(Orders orders);


    @Update("update orders set orderAudit = #{orderAudit}, orderReport = #{orderReport},orderStatus = 0 where orderId = #{orderId} ")
    int updateOrderAuditNoPass(Orders orders);



    @Select("select * from orders where orderMemberId = #{memberId} and orderAudit = 1 and  orderStatus = 1  and  (orderPay != 1 or orderPay IS  NULL)")
    @Results({
            @Result(id = true, property = "orderId", column = "orderId"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderCreateTime", column = "orderCreateTime"),
            @Result(column = "orderStartTime", property = "orderStartTime"),
            @Result(column = "orderEndTime", property = "orderEndTime"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "orderAudit", property = "orderAudit"),
            @Result(column = "orderPrice", property = "orderPrice"),
            @Result(column = "orderMemberId", property = "orderMemberId"),
            @Result(column = "orderUsername", property = "orderUsername"),
            @Result(column = "orderReport", property = "orderReport"),
            @Result(column = "orderResult", property = "orderResult"),
            @Result(column = "orderProductId", property = "product", javaType = Product.class, one = @One(select = "com.admin.mapper.ProductMapper.selectByPrimaryKey"))
    })
    List<Orders> selectByMemberPrimaryKey(Long memberId);

    @Update("update orders set orderPay = #{orderPay}, orderResult = #{orderResult} where orderId = #{orderId} ")
    int updateOrderPayPass(Orders orders);

    @Update("update orders set orderPay = #{orderPay}, orderResult = #{orderResult} ,orderStatus = 0  where orderId = #{orderId} ")
    int updateOrderPayNoPass(Orders orders);


}
