<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                产品管理
                <small>产品表单</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/index.jsp"><i
                        class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/product/findAll.do">产品管理</a></li>
                <li class="active">产品表单</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <form action="${pageContext.request.contextPath}/product/save.do" method="post">

            <!-- 正文区域 -->
            <section class="content">
                <!--产品信息-->

                <div class="panel panel-default">
                    <div class="panel-heading">产品信息</div>
                    <div class="row data-type">

                        <div class="col-md-2 title">产品名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="productName" placeholder="产品名称" value="">
                        </div>
                        <div class="col-md-2 title">合同名称</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" name="productContract" placeholder="合同名称" value="">
                        </div>


                        <div class="col-md-2 title">产品价格</div>
                        <div class="col-md-4 data">
                            <input type="text" class="form-control" placeholder="产品价格" name="productPrice" value="">
                        </div>

                        <div class="col-md-2 title">产品状态</div>
                        <div class="col-md-4 data">
                            <select class="form-control select2" style="width: 100%" name="productStatus">
                                <option value="0" selected="selected">关闭</option>
                                <option value="1">开启</option>
                            </select>
                        </div>

                        <div class="col-md-2 title rowHeight2x">产品描述</div>
                        <div class="col-md-10 data rowHeight2x">
                            <textarea class="form-control" rows="3" placeholder="其他信息" name="productDesc"></textarea>
                        </div>

                    </div>
                </div>
                <!--订单信息/-->

                <!--工具栏-->
                <div class="box-tools text-center">
                    <button type="submit" class="btn bg-maroon">保存</button>
                    <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                </div>
                <!--工具栏/-->
            </section>
            <!-- 正文区域 /-->
        </form>
    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>