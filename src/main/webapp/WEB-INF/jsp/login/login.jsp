<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <%@ include file="/WEB-INF/jsp/common/commonHead.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login</title>
</head>

<body class="bg-gradient-primary">

<div class="container">

    <!-- Outer Row -->
    <div class="row justify-content-center">

        <div class="col-xl-10 col-lg-12 col-md-9">

            <div class="card o-hidden border-0 shadow-lg my-5">
                <div class="card-body p-0">
                    <!-- Nested Row within Card Body -->
                    <div class="row">
                        <div class="col-lg-6 d-none d-lg-block bg-login-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-4">환영합니다!</h1>
                                </div>
                                <form class="user" id="frmLogin" action="${pageContext.request.contextPath}/login" method="post">
                                    <div class="form-group">
                                        <label for="username"></label>
                                        <input type="email" class="form-control form-control-user"
                                               id="username" name="username" aria-describedby="emailHelp"
                                               placeholder="Enter Email Address..." form="frmLogin">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user"
                                               id="password" name="password" placeholder="Password" form="frmLogin">
                                    </div>
                                    <div class="form-group">
                                        <div class="custom-control custom-checkbox small">
                                            <input type="checkbox" class="custom-control-input" id="customCheck">
                                            <label class="custom-control-label" for="customCheck">Remember Me</label>
                                        </div>
                                    </div>
                                </form>
                                <a id="submitBtn" class="btn btn-primary btn-user btn-block">Login</a>
                                <hr>
                                <a href="index.html" class="btn btn-google btn-user btn-block"><i class="fab fa-google fa-fw"></i> Login with Google</a>
                                <a href="index.html" class="btn btn-facebook btn-user btn-block"><i class="fab fa-facebook-f fa-fw"></i> Login with Facebook</a>
                                <hr>
                                <div class="text-center">
                                    <a class="small" href="forgot-password.html">Forgot Password?</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/join/register">Create an Account!</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
<%@ include file="/WEB-INF/jsp/common/commonBody.jsp" %>
<script>
    function submit() {
        let username = $("#username").val();
        let password = $("#password").val();
        if (username === "" || password === "") {
            alert("아이디와 비밀번호를 입력해주세요.");
            return;
        }
        $("#frmLogin").submit();
    }

    $(document).ready(function () {
        $("#submitBtn").click(function () {
            $("#frmLogin").submit();
        });
    });

</script>

</body>

</html>