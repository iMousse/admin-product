<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/tag" %>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">

    <jsp:include page="alpha-resources.jsp"/>



</head>

<body class="hold-transition skin-purple sidebar-mini">

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
                    <h3 class="box-title">产品经理</h3>
                </div>


                <!-- 表格数据 -->
                <div class="box-body">
                    <!-- 数据表格 -->
                    <div class="table-box">
                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th></th>
                                <th>姓名</th>
                                <th>邮箱</th>
                                <th>手机号</th>
                                <th>用户状态</th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${users}" var="user">

                                <tr>
                                    <td><input name="orderIds" type="checkbox"></td>
                                    <td>${user.username }</td>
                                    <td>${user.userEmail}</td>
                                    <td>${user.userPhone}</td>
                                    <td>${user.userStatusStr}</td>
                                    <td>
                                        <button type="button" class="btn bg-olive btn-xs" onclick="location.href='${pageContext.request.contextPath}/orders/pick.do?username=${user.username}&orderId=${orderId}'">发送订单</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->
                    </div>

                </div>
                <!-- 表格数据 -->

                

            </div>

        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>