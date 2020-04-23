<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/tag" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/user0.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p>${member.memberName}</p>
                <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <li id="admin-index">
                <a href="${pageContext.request.contextPath}/member/findAll.do">
                    <i class="fa fa-dashboard"></i>
                    <span>我的订单</span></a>
            </li>

        </ul>
    </section>
    <!-- /.sidebar -->
</aside>