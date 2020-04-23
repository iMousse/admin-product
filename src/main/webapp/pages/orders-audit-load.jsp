<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">


    <jsp:include page="alpha-resources.jsp"/>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="beta-header.jsp"/>
    <!-- 页面头部 /-->

    <!-- 导航侧栏 -->
    <jsp:include page="beta-sidebar.jsp"/>
    <!-- 导航侧栏 /-->

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                生成订单
                <small>产品管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="all-order-manage-list.html">订单管理</a></li>
                <li class="active">订单详情</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <!--产品信息-->
            <div class="panel panel-default">
                <div class="panel-heading">产品信息</div>
                <div class="row data-type">

                    <div class="col-md-2 title">产品名称</div>
                    <div class="col-md-4 data">
                        <input type="hidden" name='productId' value="${orders.product.productId}">
                        <input type="text" class="form-control" value="${orders.product.productName }" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">产品创建时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                            <input type="text" class="form-control pull-right" readonly="readonly" value="${orders.product.productStartTimeStr}">
                        </div>
                    </div>

                    <div class="col-md-2 title">产品协议</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" value="${orders.product.productContract }" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">产品状态</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" value="${orders.product.productStatusStr }" readonly="readonly">
                    </div>


                    <div class="col-md-2 title">产品价格</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" value="${orders.product.productPrice}" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">产品描述</div>
                    <div class="col-md-4 data">
                        <textarea class="form-control" rows="3" readonly="readonly">${orders.product.productDesc}</textarea>
                    </div>

                </div>
            </div>
            <!--产品信息-->

            <!--甲方信息-->
            <div class="panel panel-default">
                <div class="panel-heading">甲方信息</div>

                <div class="row data-type">

                    <div class="col-md-2 title">甲方名称</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="memberName" value="${orders.member.memberName}" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">甲方手机</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="memberPhone" value="${orders.member.memberPhone}" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">甲方邮箱</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="memberEmail" value="${orders.member.memberEmail}" readonly="readonly">
                    </div>
                </div>

            </div>

            <!-- 订单信息 -->
            <div class="panel panel-default">
                <div class="panel-heading">订单信息</div>
                <div class="row data-type">

                    <div class="col-md-2 title">产品数量</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderProductNum" value="${orders.orderProductNum}" readonly="readonly">
                    </div>


                    <div class="col-md-2 title">订单价格</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderPrice" value="${orders.orderPrice}" readonly="readonly">
                    </div>


                    <div class="col-md-2 title">订单开始时间</div>
                    <div class="col-md-4 data">
                        <div class="input-group date">
                            <div class="input-group-addon"><i class="fa fa-calendar"></i></div>
                            <input type="text" class="form-control pull-right" id="datepicker-a3" name="orderStartTime" value="${orders.orderStartTimeStr}" readonly="readonly">
                        </div>
                    </div>

                    <div class="col-md-2 title">订单状态</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderStatusStr" value="${orders.orderStatusStr}" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">订单审核</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderAuditStr" value="${orders.orderAuditStr}" readonly="readonly">
                    </div>


                    <div class="col-md-2 title">订单支付</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderPayStr" value="${orders.orderPayStr}" readonly="readonly">
                    </div>

                    <div class="col-md-2 title">订单描述</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderDesc" value="${orders.orderDesc}" readonly="readonly">
                    </div>


                    <div class="col-md-2 title">验收结果</div>
                    <div class="col-md-4 data text">
                        <input type="text" class="form-control" name="orderResult" value="${orders.orderResult}" readonly="readonly">
                    </div>

                </div>


            </div>

            <form action="${pageContext.request.contextPath}/orders/auditManger.do" method="post">

                <div class="panel panel-default">
                    <div class="panel-heading">审核信息</div>
                    <input type="hidden" name="orderId" value="${orders.orderId}">
                    <div class="row data-type">

                        <div class="col-md-2 title">审核</div>
                        <div class="col-md-4 data text">
                            <select class="form-control" name="orderAudit">
                                <option value="0">审核不通过</option>
                                <option value="1">审核通过</option>
                            </select>
                        </div>

                        <div class="col-md-2 title">验收报告</div>
                        <div class="col-md-4 data text">
                            <input type="text" class="form-control" name="orderReport">
                        </div>
                    </div>

                </div>


                <!--工具栏-->
                <div class="box-tools text-center">
                    <button type="submit" class="btn bg-maroon">提交</button>
                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                </div>
            </form>
            <!--工具栏/-->
        </section>
        <!-- 正文区域 /-->


    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>


</html>