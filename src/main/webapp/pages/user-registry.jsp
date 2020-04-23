<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版 | Sign up</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function () {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });

        $(function () {
            var usernameCondition = false;
            var userEmailCondition = false;
            var userPhoneCondition = false;
            var passwordCondition = false;
            var repasswordCondition = false;


            var password;


            $("#username").blur(function () {

                var username = $(this).val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/user/username.do",
                    data: {"username": username},
                    type: "post",
                    dataType: "json",
                    success: function (ajaxResult) {
                        if (ajaxResult.name === "success") {
                            $("#usernameMsg").text("用户名可用").css("color", "green");
                            usernameCondition = true;
                        }
                        if (ajaxResult.name === "fail") {
                            $("#usernameMsg").text("用户名已存在").css("color", "red");
                        }
                    }
                });
            });


            $("#userEmail").blur(function () {
                var userEmail = $(this).val();


                var reg = /\S*@\S*\.\S*/;

                if (userEmail !== "") {
                    if (!reg.test(userEmail)) {
                        $("#userEmailMsg").text("邮箱名格式不正确").css("color", "red");
                        return;
                    }
                } else {
                    $("#userEmailMsg").text("请输入邮箱").css("color", "red");
                    return;
                }

                $.ajax({
                    url: "${pageContext.request.contextPath}/user/email.do",
                    data: {"userEmail": userEmail},
                    type: "post",
                    dataType: "json",
                    success: function (ajaxResult) {
                        if (ajaxResult.name === "success") {
                            $("#userEmailMsg").text("邮箱名可用").css("color", "green");
                            userEmailCondition = true;
                        }
                        if (ajaxResult.name === "fail") {
                            $("#userEmailMsg").text("邮箱名已存在").css("color", "red");
                        }
                    }
                });
            });

            $("#userPhone").blur(function () {
                var userPhone = $(this).val();

                //手机号码
                var reg = /^1[3|4|5|7|8][0-9]{9}$/;

                if (userPhone !== "") {
                    if (reg.test(userPhone)) {
                        $("#userPhoneMsg").text("手机号可用").css("color", "green");
                        userPhoneCondition = true;
                    } else {
                        $("#userPhoneMsg").text("手机号不可用").css("color", "red");
                    }
                } else {
                    $("#userPhoneMsg").text("请输入手机号").css("color", "red");
                }

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


            $("#submit").click(function () {
                if (usernameCondition && userEmailCondition && userPhoneCondition && passwordCondition && repasswordCondition) {
                    $("form").submit();
                } else {
                    return false;
                }
            });


        });
    </script>
</head>


<body class="hold-transition register-page">
<div class="register-box">

    <div class="login-logo">
        <a href="#"><b>生产经营</b>管理系统</a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg">新用户注册</p>

        <form action="${pageContext.request.contextPath}/user/registry.do" method="post">

            <div class="form-group has-feedback ">
                <input type="text" class="form-control" id="username" name="username" placeholder="用户名">
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            
            <div class="form-group has-feedback">
                <input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="Email">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>


            <div class="form-group has-feedback">
                <input type="text" class="form-control"  id="userPhone" name="userPhone" placeholder="手机号">
                <span class="glyphicon glyphicon-phone form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <input type="password" class="form-control" id="password" name="password" placeholder="密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>


            <div class="form-group has-feedback">
                <input type="password" class="form-control" id="repassword" name="repassword" placeholder="确认密码">
                <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
            </div>

            <div class="row">
                <p id="usernameMsg" class="text-center"></p>
                <p id="userEmailMsg" class="text-center"></p>
                <p id="userPhoneMsg" class="text-center"></p>
                <p id="passwordMsg" class="text-center"></p>
                <p id="repasswordMsg" class="text-center"></p>
            </div>

            <div class="row">

                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <label>
                            <a href="${pageContext.request.contextPath}/user/login.do" class="text-center">我有账号，现在就去登录</a>
                        </label>
                    </div>
                </div>

                <div class="col-xs-4">
                    <button id="submit" type="submit" class="btn btn-primary btn-block btn-flat">注册</button>
                </div>
            </div>



        </form>


    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->


</body>

</html>