<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

        $(function () {
            var ok1 = false;

            var passwordCondition = false;
            var repasswordCondition = false;


            $("#oldPwd").blur(function(){
                var password = $(this).val();
                $.ajax({
                    url:"${pageContext.request.contextPath}/user/password.do",
                    data:{"password":password},
                    type:"post",
                    dataType:"json",
                    success:function(ajaxResult){
                        if(ajaxResult.name==="success"){
                            $("#msg").text("密码正确").css("color","green");
                            ok1 = true;
                        }
                        if(ajaxResult.name==="fail"){
                            $("#msg").text("密码错误").css("color","red");
                            ok1 = false;
                        }
                    }
                });
            });

            $('#password').blur(function () {
                password = $(this).val();

                //以字母开头，长度在6~18之间，只能包含字母、数字和下划线
                var reg =/^(\w){6,20}$/;

                if (reg.test(password)) {
                    $("#passwordMsg").text("密码可用").css("color", "green");
                    passwordCondition = true;
                } else {
                    $("#passwordMsg").text("密码太简单").css("color", "red");
                }

            });

            $("#repassword").blur(function () {
                var repassword = $(this).val();

                if (password === repassword) {
                    $("#repasswordMsg").text("密码一致").css("color", "green");
                    repasswordCondition = true;
                } else {
                    $("#repasswordMsg").text("两次输入的密码不一致").css("color", "red");
                }

            });


            $("#submit").click(function(){
                if(ok1&&passwordCondition&&repasswordCondition){
                    $("form").submit();
                }else{
                    return false;
                }
            });


        });

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
            <h1>
                我的信息
                <small>信息管理</small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="${pageContext.request.contextPath}/user/index.do"><i class="fa fa-dashboard"></i> 首页</a></li>
                <li><a href="${pageContext.request.contextPath}/user/information.do">信息管理</a></li>
                <li class="active">我的信息</li>
            </ol>
        </section>
        <!-- 内容头部 /-->

        <!-- 正文区域 -->
        <section class="content">

            <!--产品信息-->
            <div class="panel panel-default">
                <div class="panel-heading">用户信息</div>
                <div class="row data-type">

                    <div class="col-md-2 title">用户名称</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" id="username" name="username" value="${user.username}" readonly>
                    </div>

                    <div class="col-md-2 title">邮箱</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" name="userEmail" value="${user.userEmail}" readonly>
                    </div>


                    <div class="col-md-2 title">联系电话</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" name="userPhone" value="${user.userPhone}" readonly>
                    </div>


                    <div class="col-md-2 title">用户状态</div>
                    <div class="col-md-4 data">
                        <input type="text" class="form-control" name="userStatus" value="${user.userStatusStr}" readonly>
                    </div>


                </div>
            </div>
            <!--订单信息/-->


            <c:if test="${ user.roles.size() != 0}">
                <div class="panel panel-default">
                    <div class="panel-heading">用户角色</div>

                    <c:forEach items="${user.roles}" var="role">
                        <div class="row data-type">

                            <div class="col-md-2 title">角色名称</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control"  name="apartmentNum" value="${role.roleName}" readonly>
                            </div>

                            <div class="col-md-2 title">角色描述</div>
                            <div class="col-md-4 data">
                                <input type="text" class="form-control" name="apartmentArea" value="${role.roleDesc}" readonly >
                            </div>

                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <!--工具栏-->
            <div class="box-tools text-center">
                <button type="button" class="btn bg-maroon" data-toggle="modal" data-target="#myModal">修改密码</button>
                <div id="myModal" class="modal <%--modal-primary--%>" role="dialog" style="display: none;">
                        <form action="${pageContext.request.contextPath}/user/update/pwd.do" method="post">
                            <div class="modal-dialog <%--modal-lg--%>">

                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">X</span></button>
                                        <h4 class="modal-title">修改密码</h4>
                                    </div>

                                    <div class="modal-body">
                                        <div class="box-body">
                                            <div class="form-horizontal">


                                                <div class="form-group">
                                                    <label for="oldPwd" class="col-sm-2 control-label">原密码:</label>
                                                    <div class="col-sm-6">
                                                        <div class="input-group">
                                                            <input id="oldPwd" type="password" class="form-control" name="oldPwd" placeholder="原密码">
                                                            <span  id="msg" class="input-group-addon">必须与原来的相同</span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label for="password" class="col-sm-2 control-label">密码:</label>
                                                    <div class="col-sm-6">
                                                        <div class="input-group">
                                                            <input id="password" type="password" class="form-control" name="newPwd" placeholder="密码">
                                                            <span id="passwordMsg" class="input-group-addon">长度在6~18之间</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                
                                                <div class="form-group">
                                                    <label for="repassword" class="col-sm-2 control-label">确认密码:</label>
                                                    <div class="col-sm-6">
                                                        <div class="input-group">
                                                            <input id="repassword" type="password" class="form-control" name="repassword" placeholder="确认密码">
                                                            <span id="repasswordMsg" class="input-group-addon">重复您的密码</span>
                                                        </div>
                                                    </div>
                                                </div>



                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="button" class="btn bg-maroon" data-dismiss="modal">关闭</button>
                                        <button id="submit" type="submit" class="btn bg-blue">保存</button>
                                    </div>
                                </div>
                                <!-- /.modal-content -->
                            </div>

                            <!-- /.modal-dialog -->
                        </form>
                    </div>
                <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
            </div>
            <!--工具栏/-->


        </section>
        <!-- 正文区域 /-->

    </div>
    <!-- 内容区域 /-->

    <jsp:include page="beta-footer.jsp"/>

</div>


</body>

</html>