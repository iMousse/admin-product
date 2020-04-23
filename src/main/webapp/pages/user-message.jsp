<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="http://java.sun.com/jsp/jstl/tag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script>

        function changePageSize() {
            //获取下拉框的值
            var pageSize = $("#changePageSize").val();

            //向服务器发送请求，改变没页显示条数
            location.href = "${pageContext.request.contextPath}/user/message.do?page=1&size=" + pageSize;
        }


    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">

<div class="wrapper">

    <jsp:include page="beta-header.jsp"/>

    <jsp:include page="beta-sidebar.jsp"/>

    <!-- 内容区域 -->
    <div class="content-wrapper">

        <!-- 内容头部 -->
        <section class="content-header">
            <h1>我的留言<small>基础数据</small></h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/user/index.do"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/message.do">我的留言</a></li>
                <li class="active">我的留言</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content"> <!-- .box-body -->
            <div class="box box-primary">
                <div class="box-header with-border">
                     <h3 class="box-title">留言列表</h3>
                </div>

                <div class="box-body">

                    <!-- 功能栏 -->
                    <button type="button" class="btn btn-default" title="刷新" onclick="location.href='${pageContext.request.contextPath}/user/message.do'">
                        <i class="fa fa-refresh"></i> 页面刷新
                    </button>

                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addMessage">
                        <i class="fa fa-file-o"></i> 添加留言
                    </button>
                    <div id="addMessage" class="modal" role="dialog" style="display: none;">
                        <form action="${pageContext.request.contextPath}/user/message/add.do" method="post">
                            <div class="modal-dialog">

                                <div class="modal-content">

                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">X</span></button>
                                        <h4 class="modal-title text-center">添加留言</h4>
                                    </div>

                                    <div class="modal-body">
                                        <div class="box-body">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label for="username" class="col-sm-2 control-label">留言主题:</label>
                                                    <div class="col-sm-10">
                                                        <input  type="text" class="form-control" name="messageTitle">
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="userEmail" class="col-sm-2 control-label">留言内容:</label>
                                                    <div class="col-sm-10">
                                                        <textarea type="text" rows="3" class="form-control" name="messageContent" ></textarea>
                                                    </div>
                                                </div>
                                                
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn bg-maroon" data-dismiss="modal">关闭</button>
                                        <button type="submit" id="submit" class="btn bg-blue">保存</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>
                        </form>
                    </div>
                    <!-- 功能栏 -->


                    <!-- 数据表格 -->
                    <div class="table-box">


                        <!--数据列表-->
                        <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                            <thead>
                            <tr>
                                <th class="" style="padding-right: 0px">
                                    <input id="selall" type="checkbox" class="icheckbox_square-blue">
                                </th>
                                <th>留言标题</th>
                                <th>创建时间</th>
                                <th class="text-center">状态</th>
                                <th class="text-center">操作</th>
                            </tr>
                            </thead>

                            <tbody>

                            <c:forEach items="${pageInfo.list}" var="message" varStatus="num">
                                <tr>
                                    <td><input name="messageId" type="checkbox" value="${message.messageId}"></td>
                                    <td>${message.messageTitle }</td>
                                    <td><fmt:formatDate value="${message.messageStartDate}" pattern="yyyy-MM-dd"/></td>
                                    <td class="text-center">${message.messageStatusStr}</td>
                                    <c:if test="${message.messageStatus == 0}">
                                        <td class="text-center">
                                            <button type="button" class="btn bg-maroon btn-xs" data-toggle="modal" data-target="#messageUpdate${num.index + 1}">修改留言</button>
                                            <div id="messageUpdate${num.index + 1}" class="modal" role="dialog" style="display: none;">
                                                <form action="${pageContext.request.contextPath}/user/message/update.do" method="post">
                                                    <div class="modal-dialog">

                                                        <div class="modal-content">

                                                            <input type="hidden" name="messageId" value="${message.messageId}">
                                                            
                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">X</span></button>
                                                                <h4 class="modal-title text-center">修改留言</h4>
                                                            </div>

                                                            <div class="modal-body">
                                                                <div class="box-body">
                                                                    <div class="form-horizontal">
                                                                        <div class="form-group">
                                                                            <label for="username" class="col-sm-2 control-label">留言主题:</label>
                                                                            <div class="col-sm-10">
                                                                                <input  type="text" class="form-control" name="messageTitle" value="${message.messageTitle}">
                                                                            </div>
                                                                        </div>
                                                                        
                                                                        <div class="form-group">
                                                                            <label for="userEmail" class="col-sm-2 control-label">留言内容:</label>
                                                                            <div class="col-sm-10">
                                                                                <textarea type="text" rows="3" class="form-control" name="messageContent">${message.messageContent}</textarea>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="modal-footer">
                                                                <button type="button" class="btn bg-maroon" data-dismiss="modal">关闭</button>
                                                                <button type="submit" id="submit" class="btn bg-blue">保存</button>
                                                            </div>
                                                        </div>
                                                        <!-- /.modal-content -->
                                                    </div>
                                                </form>
                                            </div>
                                        </td>
                                    </c:if>
                                    <c:if test="${message.messageStatus == 1}">
                                        <td class="text-center">
                                            <button type="button" class="btn bg-blue btn-xs" data-toggle="modal" data-target="#messageDetail${num.index + 1}">留言详情</button>
                                            <div id="messageDetail${num.index + 1}" class="modal" role="dialog" style="display: none;">
                                                    <div class="modal-dialog">

                                                        <div class="modal-content">

                                                            <div class="modal-header">
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">X</span></button>
                                                                <h4 class="modal-title text-center">留言详情</h4>
                                                            </div>

                                                            <div class="modal-body">
                                                                <div class="box-body">
                                                                    <div class="form-horizontal">
                                                                        <div class="form-group">
                                                                            <label for="username" class="col-sm-2 control-label">留言主题:</label>
                                                                            <div class="col-sm-10">
                                                                                <input  type="text" class="form-control" name="messageTitle" value="${message.messageTitle}" readonly>
                                                                            </div>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label for="userEmail" class="col-sm-2 control-label">留言内容:</label>
                                                                            <div class="col-sm-10">
                                                                                <textarea type="text" rows="3" class="form-control" name="messageContent" readonly>${message.messageContent}</textarea>
                                                                            </div>
                                                                        </div>

                                                                        <div class="form-group">
                                                                            <label for="userEmail" class="col-sm-2 control-label">留言内容:</label>
                                                                            <div class="col-sm-10">
                                                                                <textarea type="text" rows="3" class="form-control" name="messageResult" readonly>${message.messageResult}</textarea>
                                                                            </div>
                                                                        </div>

                                                                    </div>
                                                                </div>
                                                            </div>

                                                            <div class="modal-footer">
                                                                <button type="button" class="btn bg-maroon" data-dismiss="modal">关闭</button>
                                                            </div>
                                                        </div>
                                                        <!-- /.modal-content -->
                                                    </div>
                                            </div>
                                        </td>
                                    </c:if>

                                </tr>
                            </c:forEach>
                            </tbody>

                        </table>

                    </div>
                    <!-- 数据表格 -->

                </div>

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
                            <li><a href="${pageContext.request.contextPath}/user/message.do?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/message.do?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>
                            <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum">
                                <li><a href="${pageContext.request.contextPath}/user/message.do?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                            </c:forEach>
                            <li><a href="${pageContext.request.contextPath}/user/message.do?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                            <li><a href="${pageContext.request.contextPath}/user/message.do?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a></li>
                        </ul>
                    </div>

                </div>
                <!-- /.box-footer-->

            </div>



        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>