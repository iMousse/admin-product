<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
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
</head>


<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="beta-header.jsp"/>

    <jsp:include page="beta-sidebar.jsp"/>

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                404 错误页面
            </h1>

            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/user/index.do"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li class="active">404 错误</li>
            </ol>

        </section>

        <!-- Main content -->
        <section class="content">
            <div class="error-page">
                <h2 class="headline text-yellow"> 404</h2>

                <div class="error-content">
                    <h3><i class="fa fa-warning text-yellow"></i> Oops! 页面没有找到.</h3>

                    <p>
                        没有找到你请求的页面, 你可以 <a href="${pageContext.request.contextPath}/user/index.do">返回到后台首页</a> 或者通过搜索查询
                    </p>

                </div>
                <!-- /.error-content -->
            </div>
            <!-- /.error-page -->
        </section>
        <!-- /.content -->
    </div>

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>