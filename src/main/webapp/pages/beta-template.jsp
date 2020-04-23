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

        <img src="${pageContext.request.contextPath}/img/center.jpg" width="100%" height="100%"/>

    </div>

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>