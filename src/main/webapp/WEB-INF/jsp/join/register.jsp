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
    <title>회원가입</title>
</head>

<body class="bg-gradient-primary">

<div class="container">

    <div class="card o-hidden border-0 shadow-lg my-5">
        <div class="card-body p-0">
            <!-- Nested Row within Card Body -->
            <div class="row">
                <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                <div class="col-lg-7">
                    <div class="p-5">
                        <div class="text-center">
                            <h1 class="h4 text-gray-900 mb-4">Create an Account!</h1>
                        </div>
                        <form class="user">
                            <div class="form-group">
                                <input type="text" class="form-control form-control-user" id="inputName"
                                       placeholder="Name">
                            </div>
                            <div class="form-group">
                                <input type="email" class="form-control form-control-user" id="inputEmail"
                                       placeholder="Email Address">
                            </div>
                            <div class="form-group row">
                                <div class="col-sm-6 mb-3 mb-sm-0">
                                    <input type="password" class="form-control form-control-user" id="inputPassword"
                                           placeholder="Password">
                                </div>
                                <div class="col-sm-6">
                                    <input type="password" class="form-control form-control-user" id="repeatPassword"
                                           placeholder="Repeat Password">
                                </div>
                            </div>
                            <a id="goRegister" href="" class="btn btn-primary btn-user btn-block">
                                Register Account
                            </a>
                            <hr>
                            <a href="index.html" class="btn btn-google btn-user btn-block">
                                <i class="fab fa-google fa-fw"></i> Register with Google
                            </a>
                            <a href="index.html" class="btn btn-facebook btn-user btn-block">
                                <i class="fab fa-facebook-f fa-fw"></i> Register with Facebook
                            </a>
                        </form>
                        <hr>
                        <div class="text-center">
                            <a class="small" href="forgot-password.html">Forgot Password?</a>
                        </div>
                        <div class="text-center">
                            <a class="small" href="${pageContext.request.contextPath}/login">Already have an account?
                                Login!</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<%@ include file="/WEB-INF/jsp/common/commonBody.jsp" %>
<script>
    $(document).ready(function () {
        $("#goRegister").click(function (e) {
            e.preventDefault();
            let name = $("#inputName").val();
            let email = $("#inputEmail").val();
            let password = $("#inputPassword").val();
            let repeatPassword = $("#repeatPassword").val();
            if (name == "") {
                alert("이름을 입력해주세요.");
                $("#inputName").focus();
                return false;
            }
            if (email == "") {
                alert("이메일을 입력해주세요.");
                $("#inputEmail").focus();
                return false;
            }
            if (password == "") {
                alert("비밀번호를 입력해주세요.");
                $("#inputPassword").focus();
                return false;
            }
            if (repeatPassword == "") {
                alert("비밀번호를 입력해주세요.");
                $("#repeatPassword").focus();
                return false;
            }
            if (password != repeatPassword) {
                alert("비밀번호가 일치하지 않습니다.");
                $("#repeatPassword").focus();
                return false;
            }

            let params = {
                userName: name,
                loginId: email,
                userPwd: password
            };

            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/join/register",
                data: JSON.stringify(params),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function (data) {
                alert("회원가입이 완료되었습니다.");
                location.href = "${pageContext.request.contextPath}/login";
            }).fail(function (xhr, status, errorThrown) {
                console.log(xhr);
                alert("회원가입에 실패하였습니다.");
            });
        });
    });
</script>
</body>

</html>