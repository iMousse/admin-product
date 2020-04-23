<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/tag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>ITCAST - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <jsp:include page="alpha-resources.jsp"/>
    <script>
        function changePageSize() {
            //获取下拉框的值
            var pageSize = $("#changePageSize").val();

            //向服务器发送请求，改变没页显示条数
            location.href = "${pageContext.request.contextPath}/orders/audit.do?page=1&size=" + pageSize;
        }
    </script>
</head>


<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="member-header.jsp"/>

    <jsp:include page="member-siderbar.jsp"/>

    <!-- 内容区域 -->

    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                数据管理
                <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li><a href="#">数据管理</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <div class="box box-primary">

                <div class="box-header with-border">
                    <h3 class="box-title">订单列表</h3>
                </div>


                <!-- 表格数据 -->
                <div class="box-body">
                    <!-- 数据表格 -->
                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px;"><input id="selall" type="checkbox" class="icheckbox_square-blue"></th>
                                <th>订单号</th>
                                <th>产品名称</th>
                                <th>订单价格</th>
                                <th>下单时间</th>
                                <th>开始时间</th>
                                <th>管理员审核</th>
                                <th>支付</th>
                                <th>操作</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${pageInfo.list}" var="orders">

                                <tr>
                                    <td><input name="orderIds" type="checkbox"></td>
                                    <td>${orders.orderNum }</td>
                                    <td>${orders.product.productName }</td>
                                    <td>${orders.orderPrice }</td>
                                    <td>${orders.orderCreateTimeStr}</td>
                                    <td>${orders.orderStartTimeStr }</td>
                                    <td>${orders.orderAuditStr }</td>
                                    <td>${orders.orderPayStr }</td>
                                    <td>
                                        <button type="button" class="btn bg-olive btn-xs" onclick="location.href='${pageContext.request.contextPath}/member/auditLoad.do?orderId=${orders.orderId}'">审核</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                    </div>

                </div>
                <!-- 表格数据 -->

                <!-- 分页管理 -->
                <div class="box-footer">
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            总共${pageInfo.pages}页，共${pageInfo.total}条数据。 每页
                            <select class="form-control" id="changePageSize" onchange="changePageSize()">
                                <my:option pageSize="${pageInfo.pageSize}"/>
                            </select> 条
                        </div>
                    </div>

                    <div class="box-tools pull-right">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/member/findAll.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/member/findAll.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>

                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <li><a href="${pageContext.request.contextPath}/member/findAll.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                            </c:forEach>

                            <li><a href="${pageContext.request.contextPath}/member/findAll.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                            <li><a href="${pageContext.request.contextPath}/member/findAll.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a></li>
                        </ul>
                    </div>

                </div>
                <!-- 分页管理 -->

            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>
