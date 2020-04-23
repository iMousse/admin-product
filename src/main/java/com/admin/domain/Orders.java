package com.admin.domain;

import com.admin.constant.Constant;
import com.admin.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-21
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class Orders {
    private Long orderId;
    private String orderNum;
    private Date orderCreateTime;
    private String orderCreateTimeStr;

    @DateTimeFormat(pattern = "yyyy年MM月dd") //作用只在成员变量上
    private Date orderStartTime;
    private String orderStartTimeStr;
    private Date orderEndTime;
    private String orderEndTimeStr;

    private String orderDesc;
    private Integer orderStatus;
    private BigDecimal orderPrice;

    private String orderStatusStr;
    private Integer orderAudit;
    private String orderAuditStr;
    private Integer orderPay;
    private String orderPayStr;

    private Long orderProductId;
    private Integer orderProductNum;

    private Long orderMemberId;

    private String orderUsername;

    private String orderReport;
    private String orderResult;


    //一个订单对应一个产品
    private Product product;

    //一个订单对应一个会员
    private Member member;

    //一个订单对应一个产品经理
    private User user;


    public String getOrderEndTimeStr() {
        if (orderEndTime != null) {

            orderEndTimeStr = DateUtils.date2String(orderEndTime, "yyyy-MM-dd");
        }

        return orderEndTimeStr;
    }

    public void setOrderEndTimeStr(String orderEndTimeStr) {
        this.orderEndTimeStr = orderEndTimeStr;
    }

    public String getOrderCreateTimeStr() {

        if (orderCreateTime != null) {

            orderCreateTimeStr = DateUtils.date2String(orderCreateTime, "yyyy-MM-dd");
        }

        return orderCreateTimeStr;
    }

    public void setOrderCreateTimeStr(String orderCreateTimeStr) {
        this.orderCreateTimeStr = orderCreateTimeStr;
    }

    public String getOrderStartTimeStr() {
        if (orderStartTime != null) {

            orderStartTimeStr = DateUtils.date2String(orderStartTime, "yyyy-MM-dd");
        }

        return orderStartTimeStr;
    }

    public void setOrderStartTimeStr(String orderStartTimeStr) {
        this.orderStartTimeStr = orderStartTimeStr;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderProductNum() {
        return orderProductNum;
    }

    public void setOrderProductNum(Integer orderProductNum) {
        this.orderProductNum = orderProductNum;
    }

    public Date getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Date orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Long getOrderMemberId() {
        return orderMemberId;
    }

    public void setOrderMemberId(Long orderMemberId) {
        this.orderMemberId = orderMemberId;
    }

    public String getOrderUsername() {
        return orderUsername;
    }

    public void setOrderUsername(String orderUsername) {
        this.orderUsername = orderUsername;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusStr() {

        if (orderStatus.equals(Constant.OrderStatus.ORDERS_OPEN)) {
            orderStatusStr = "开启";
        }

        if (orderStatus.equals(Constant.OrderStatus.ORDERS_DONE)) {
            orderStatusStr = "完成";
        }

        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public Integer getOrderAudit() {
        return orderAudit;
    }

    public void setOrderAudit(Integer orderAudit) {
        this.orderAudit = orderAudit;
    }

    public String getOrderAuditStr() {

        if (orderAudit == null) {

            orderAuditStr = "未审核";

            if (orderStatus != null && orderStatus == 1) {

                orderAuditStr = "订单正在审核中";
            }


        } else {

            if (orderAudit == 0) {
                orderAuditStr = "审核未通过";
            } else if (orderAudit == 1) {
                orderAuditStr = "审核通过";
            }
        }

        return orderAuditStr;
    }

    public void setOrderAuditStr(String orderAuditStr) {
        this.orderAuditStr = orderAuditStr;
    }

    public Integer getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(Integer orderPay) {
        this.orderPay = orderPay;
    }

    public String getOrderPayStr() {
        if (orderPay == null) {

            orderPayStr = "未支付";

            if (orderAudit != null && orderAudit == 1) {

                orderPayStr = "正在审核中";
            }


        } else {
            if (orderPay == 0) {
                orderPayStr = "审核未通过";
            } else if (orderPay == 1) {
                orderPayStr = "支付";
            }
        }

        return orderPayStr;
    }

    public void setOrderPayStr(String orderPayStr) {
        this.orderPayStr = orderPayStr;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }


    public String getOrderReport() {

        if (orderReport == null) {
            orderReport = "未提交报告";
        }

        return orderReport;
    }

    public void setOrderReport(String orderReport) {
        this.orderReport = orderReport;
    }

    public String getOrderResult() {

        if (orderResult == null) {

            orderResult = "未提交结果";
        }
        return orderResult;
    }

    public void setOrderResult(String orderResult) {
        this.orderResult = orderResult;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", orderNum='" + orderNum + '\'' +
                ", orderCreateTime=" + orderCreateTime +
                ", orderCreateTimeStr='" + orderCreateTimeStr + '\'' +
                ", orderStartTime=" + orderStartTime +
                ", orderStartTimeStr='" + orderStartTimeStr + '\'' +
                ", orderEndTime=" + orderEndTime +
                ", orderEndTimeStr='" + orderEndTimeStr + '\'' +
                ", orderDesc='" + orderDesc + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderPrice=" + orderPrice +
                ", orderStatusStr='" + orderStatusStr + '\'' +
                ", orderAudit=" + orderAudit +
                ", orderAuditStr='" + orderAuditStr + '\'' +
                ", orderPay=" + orderPay +
                ", orderPayStr='" + orderPayStr + '\'' +
                ", orderProductId=" + orderProductId +
                ", orderProductNum=" + orderProductNum +
                ", orderMemberId=" + orderMemberId +
                ", orderUsername='" + orderUsername + '\'' +
                ", orderReport='" + orderReport + '\'' +
                ", orderResult='" + orderResult + '\'' +
                '}';
    }
}
