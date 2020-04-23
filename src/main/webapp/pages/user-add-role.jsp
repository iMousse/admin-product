<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>用户管理<small>添加角色表单</small></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/user/index.do"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/findAll.do">用户管理</a></li>
                <li class="active">添加角色表单</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <form action="${pageContext.request.contextPath}/user/addRoleToUser.do" method="post">
            <!-- 正文区域 -->
            <section class="content">

                <input type="hidden" name="userId" value="${user.userId}">

                <table id="dataList"
                       class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right: 0px">
                            <input id="selall" type="checkbox" class="icheckbox_square-blue"></th>
                        <th class="sorting_asc">ID</th>
                        <th class="sorting">角色名称</th>
                        <th class="sorting">角色描述</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${roleList}" var="role">
                        <tr>
                            <td>
                                <input name="roleIds" type="checkbox" value="${role.roleId}">
                            </td>
                            <td>${role.roleId}</td>
                            <td>${role.roleName }</td>
                            <td>${role.roleDesc}</td>

                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <!--订单信息/--> <!--工具栏-->
                <div class="box-tools text-center">
                    <button type="submit" class="btn bg-maroon">保存</button>
                    <button type="button" class="btn bg-default"
                            onclick="history.back(-1);">返回
                    </button>
                </div>
                <!--工具栏/--> </section>
            <!-- 正文区域 /-->
        </form>
    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>