package com.admin.domain;

import com.admin.constant.Constant;
import com.admin.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-02-19
 * Time: 16:22
 * To change this template use File | Settings | File Templates.
 */
public class Product implements Serializable {
    private Long productId;
    private Long productUserId;
    private String productName;
    private BigDecimal productPrice;
    private String productDesc;
    private String productContract;
    private String productContractUrl;
    private Integer productStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //作用只在成员变量上
    private Date productStartTime;

    //展示页面数据
    private String productStartTimeStr;
    private String productStatusStr;

    //一个产品对应着一个身份为销售的用户
    private User user;

    public String getProductContractUrl() {
        return productContractUrl;
    }

    public void setProductContractUrl(String productContractUrl) {
        this.productContractUrl = productContractUrl;
    }

    public Long getProductUserId() {
        return productUserId;
    }

    public void setProductUserId(Long productUserId) {
        this.productUserId = productUserId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductContract() {
        return productContract;
    }

    public void setProductContract(String productContract) {
        this.productContract = productContract;
    }

    public Date getProductStartTime() {
        return productStartTime;
    }

    public void setProductStartTime(Date productStartTime) {
        this.productStartTime = productStartTime;
    }

    public String getProductStartTimeStr() {

        if (productStartTime != null) {

            productStartTimeStr = DateUtils.date2String(productStartTime, "yyyy-MM-dd HH:mm:ss");
        }

        return productStartTimeStr;
    }

    public void setProductStartTimeStr(String productStartTimeStr) {
        this.productStartTimeStr = productStartTimeStr;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus != null) {
            if (productStatus.equals(Constant.ProductStatus.PRODUCT_CLOSE)) {
                productStatusStr = "关闭";
            }

            if (productStatus.equals(Constant.ProductStatus.PRODUCT_OPEN)) {
                productStatusStr = "开启";
            }
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }


    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productDesc='" + productDesc + '\'' +
                ", productContract='" + productContract + '\'' +
                ", productStartTime=" + productStartTime +
                ", productStartTimeStr='" + productStartTimeStr + '\'' +
                ", productStatus=" + productStatus +
                ", productStatusStr='" + productStatusStr + '\'' +
                '}';
    }
}
