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

    <script>
        function changePageSize() {
            //获取下拉框的值
            var pageSize = $("#changePageSize").val();

            //向服务器发送请求，改变没页显示条数
            location.href = "${pageContext.request.contextPath}/product/findAll.do?page=1&size=" + pageSize;
        }
    </script>
</head>

<body class="hold-transition skin-purple sidebar-mini">

<div class="wrapper">

    <!-- 页面头部 -->
    <jsp:include page="beta-header.jsp"/>
    <!-- 页面头部 /-->
    <!-- 导航侧栏 -->
    <jsp:include page="beta-sidebar.jsp"/>
    <!-- 导航侧栏 /-->

    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>
                数据管理
                <small>数据列表</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="#">数据管理</a></li>
                <li class="active">数据列表</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <!-- .box-body -->
            <div class="box box-primary">

                <div class="box-header with-border">
                    <h3 class="box-title">列表</h3>
                </div>

                <div class="box-body">

                    <!-- 数据表格 -->
                    <div class="table-box">

                        <!--工具栏-->
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default" title="新建"
                                            onclick="location.href='${pageContext.request.contextPath}/pages/product-add.jsp'">
                                        <i class="fa fa-file-o"></i> 新建
                                    </button>
                                    <button type="button" class="btn btn-default" title="开启">
                                        <i class="fa fa-check"></i> 开启
                                    </button>
                                    <button type="button" class="btn btn-default" title="刷新">
                                        <i class="fa fa-refresh"></i> 刷新
                                    </button>
                                </div>
                            </div>
                        </div>


                        <div class="box-tools pull-right">
                            <div class="has-feedback">
                                <input type="text" class="form-control input-sm" placeholder="搜索"> <span class="glyphicon glyphicon-search form-control-feedback"></span>
                            </div>
                        </div>
                        <!--工具栏/-->

                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px;">
                                    <input id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th class="sorting_desc">产品名称</th>
                                <th class="text-center">合同名称</th>
                                <th class="text-center sorting">产品价格</th>
                                <th class="sorting_asc sorting_asc_disabled">创建时间</th>
                                <th class="sorting">产品描述</th>
                                <th class="text-center">产品状态</th>
                                <th class="text-center">合同状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>
                            <tbody>


                            <c:forEach items="${pageInfo.list}" var="product" varStatus="num">
                                <tr>
                                    <td><input name="productIds" type="checkbox"></td>
                                    <input type="hidden" name="productId" value="${product.productId}"/>
                                    <td>${product.productName }</td>
                                    <td>${product.productContract}</td>
                                    <td class="text-center">${product.productPrice }</td>
                                    <td>${product.productStartTimeStr }</td>
                                    <td>${product.productDesc }</td>
                                    <td class="text-center">${product.productStatusStr }</td>
                                    <td class="text-center">
                                        <c:if test="${product.productContractUrl == null}">
                                            <button type="button" class="btn bg-blue-gradient btn-xs" data-toggle="modal" data-target="#uploadFile${num.index + 1}">上传</button>
                                            <div id="uploadFile${num.index + 1}" class="modal" role="dialog" style="display: none;">
                                                <form id="updateFile" action="${pageContext.request.contextPath}/product/upload.do" method="post" enctype="multipart/form-data">
                                                    <div class="modal-dialog">

                                                        <input type="hidden" name="productId" value="${product.productId}">

                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">X</span></button>
                                                                <h4 class="modal-title">文件上传</h4>
                                                            </div>

                                                            <div class="modal-body">
                                                                <div class="box-body">
                                                                    <div class="form-horizontal">
                                                                        <div class="form-horizontal">
                                                                            <div class="form-group">
                                                                                <label for="username" class="col-sm-2 control-label">文件</label>
                                                                                <div class="col-sm-6">
                                                                                    <div class="input-group">
                                                                                        <input type="file" class="form-control" name="upload">
                                                                                    </div>
                                                                                </div>
                                                                            </div>

                                                                        </div>
                                                                    </div>
                                                                </div>

                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn bg-maroon" data-dismiss="modal">关闭</button>
                                                                    <button type="submit" class="btn bg-blue">保存</button>
                                                                </div>
                                                            </div>
                                                            <!-- /.modal-content -->
                                                        </div>

                                                        <!-- /.modal-dialog -->
                                                    </div>
                                                </form>
                                            </div>
                                        </c:if>

                                        <c:if test="${product.productContractUrl != null}">
                                            <button type="button" class="btn bg-red-gradient btn-xs" onclick="location.href='${pageContext.request.contextPath}/user/download.do?filename=${product.productContractUrl}'">下载</button>
                                        </c:if>
                                    </td>
                                    <td class="text-center">
                                        <button type="button" class="btn bg-olive btn-xs" onclick="location.href='${pageContext.request.contextPath}/product/updateShow.do?productId=${product.productId}'">编辑</button>
                                        <button type="button" class="btn bg-olive btn-xs"  onclick="location.href='${pageContext.request.contextPath}/product/findById.do?productId=${product.productId}'">详情</button>
                                        <c:if test="${product.productStatus == 0}">
                                            <button type="button" class="btn bg-red-gradient btn-xs"  onclick="location.href='${pageContext.request.contextPath}/product/delete.do?productId=${product.productId}'">删除产品</button>
                                        </c:if>
                                        <c:if test="${product.productStatus != 0}">
                                            <button type="button" class="btn bg-olive btn-xs" onclick="location.href='${pageContext.request.contextPath}/product/generator.do?productId=${product.productId}'">生成订单</button>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--数据列表/-->

                        <!--工具栏-->


                        <!--工具栏/-->

                    </div>
                    <!-- 数据表格 /-->


                </div>
                <!-- /.box-body -->

                <!-- .box-footer-->
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
                            <li><a href="${pageContext.request.contextPath}/product/findAll.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/product/findAll.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <li><a href="${pageContext.request.contextPath}/product/findAll.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                            </c:forEach>
                            <li><a href="${pageContext.request.contextPath}/product/findAll.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                            <li><a href="${pageContext.request.contextPath}/product/findAll.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a></li>
                        </ul>
                    </div>

                </div>
                <!-- /.box-footer-->


            </div>

        </section>
        <!-- 正文区域 /-->

    </div>

    <jsp:include page="beta-footer.jsp"/>

</div>

</body>

</html>