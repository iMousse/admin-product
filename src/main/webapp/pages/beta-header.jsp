<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- 页面头部 -->
<header class="main-header">
    <!-- Logo -->
    <a href="all-admin-index.html" class="logo">
        <span class="logo-mini"><b>生产</b></span>
        <span class="logo-lg"><b>生产经营</b>管理系统</span>
    </a>
    <!-- Logo -->


    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
        <!-- Sidebar toggle button-->
        <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
        </a>
        <!-- Sidebar toggle button-->

        <!-- Navbar custom menu-->
        <div class="navbar-custom-menu">

            <ul class="nav navbar-nav">

                <!-- 用户界面-->
                <li class="dropdown user user-menu">
                    <!-- 缩略图-->
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <img src="${pageContext.request.contextPath}/img/user.png" class="user-image" alt="User Image">
                        <span class="hidden-xs">${sessionScope.user.username}</span>
                    </a>
                    <!-- 缩略图-->

                    <!-- 放大图-->
                    <ul class="dropdown-menu">
                        <!-- User image -->
                        <li class="user-header">
                            <img src="${pageContext.request.contextPath}/img/user.png" class="img-circle"
                                 alt="User Image">

                            <p>
                                ${sessionScope.user.username} - 数据管理员
                               <%--
                               <small>最后登录 11:20AM</small>
                               --%>
                            </p>
                        </li>
                        <!-- User image -->

                        <!-- Menu Body-->
                        <%--
                        <li class="user-body">
                            <div class="row">
                                <div class="col-xs-4 text-center">
                                    <a href="#">Followers</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Sales</a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <a href="#">Friends</a>
                                </div>
                            </div>
                        </li>
                        --%>
                        <!-- Menu Footer-->

                        <!-- User footer -->
                        <li class="user-footer">
                            <div class="pull-left">
                                <a href="#" class="btn btn-default btn-flat">修改密码</a>
                            </div>
                            <div class="pull-right">
                                <a href="${pageContext.request.contextPath}/user/logout.do"
                                   class="btn btn-default btn-flat">注销</a>
                            </div>
                        </li>
                        <!-- User footer -->

                    </ul>
                    <!-- 放大图-->
                </li>
                <!-- 用户界面-->

            </ul>
        </div>
        <!-- Navbar custom menu-->
    </nav>
    <!-- Header Navbar: style can be found in header.less -->


</header>
<!-- 页面头部 /-->