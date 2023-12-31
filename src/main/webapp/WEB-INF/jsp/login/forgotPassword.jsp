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

    <title>비밀번호 찾기</title>

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
                        <div class="col-lg-6 d-none d-lg-block bg-password-image"></div>
                        <div class="col-lg-6">
                            <div class="p-5">
                                <div class="text-center">
                                    <h1 class="h4 text-gray-900 mb-2">Forgot Your Password?</h1>
                                    <p class="mb-4">We get it, stuff happens. Just enter your email address below and we'll send you a link to reset your password!</p>
                                </div>
                                <form class="user">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" id="inputLoginId" aria-describedby="emailHelp" placeholder="Enter Your Id">
                                    </div>
                                    <a id="postInputId" class="btn btn-primary btn-user btn-block">다음</a>
                                </form>
                                <hr>
                                <form class="user" id="requestAuthCodeForm" style="display: none">
                                    <div class="form-group">
                                        <input type="email" class="form-control form-control-user" id="inputEmail" aria-describedby="emailHelp" placeholder="Enter Email Address...">
                                    </div>
                                    <a id="requestAuthCode" class="btn btn-primary btn-user btn-block">인증번호 받기</a>
                                    <hr>
                                </form>
                                <form class="user" id="inputAuthCodeForm" style="display: none">
                                    <div class="form-group">
                                        <input type="text" class="form-control form-control-user" id="inputAuthCode" aria-describedby="emailHelp" placeholder="Enter AuthCode">
                                    </div>
                                    <a id="sendAuthCode" class="btn btn-primary btn-user btn-block">인증번호 전송</a>
                                    <hr>
                                </form>
                                <form class="user" id="resetPwdForm" style="display: none">
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" id="inputNewPwd" aria-describedby="emailHelp" placeholder="Enter New Password" autocomplete="off">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control form-control-user" id="inputNewPwdConfirm" aria-describedby="emailHelp" placeholder="ReEnter New Password" autocomplete="off">
                                    </div>
                                    <a id="resetNewPwd" class="btn btn-primary btn-user btn-block">비밀번호 변경</a>
                                    <hr>
                                </form>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/join/register">Create an Account!</a>
                                </div>
                                <div class="text-center">
                                    <a class="small" href="${pageContext.request.contextPath}/login">Already have an account? Login!</a>
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

    let getTime = new Date();

    function checkElapsedTime() {
        let now = new Date();
        let elapsedTime = now - getTime;
        return Math.floor(elapsedTime / 1000);
    }

    $(document).ready(function () {
        $('#postInputId').on('click', function () {
            $("#requestAuthCodeForm").hide();
            let params = {};
            params.loginId = $("#inputLoginId").val();
            let settings = {};
            settings.url = "/auth/forgotPwd/checkLoginId";
            settings.async = true;
            settings.data = JSON.stringify(params);
            settings.success = function (data) {

                if (data.result === 0) {
                    alert("존재하지 않는 아이디입니다.");
                } else {
                    $("#requestAuthCodeForm").show();
                }

            };
            gnAjaxPost(settings)
        });

        $("#requestAuthCode").on('click', function () {
            $("#inputAuthCodeForm").hide();
            $('#inputAuthCode').val("");
            $('#resetPwdForm').hide();
            $('#resetNewPwd').val("");
            let params = {};
            params.email = $("#inputEmail").val();
            let settings = {};
            settings.url = "/auth/forgotPwd/mailSend/requestAuthCode";
            settings.async = true;
            settings.data = JSON.stringify(params);
            settings.success = function (data) {
                getTime = new Date();
                $("#requestAuthCodeForm").hide();
                $("#inputAuthCodeForm").show();
            };
            gnAjaxPost(settings)
        });
    })

    $('#sendAuthCode').on('click', function () {
        let params = {};
        params.authCode = $("#inputAuthCode").val();
        params.elapsedTime = checkElapsedTime();
        let settings = {};
        settings.url = "/auth/forgotPwd/checkAuthCode";
        settings.async = true;
        settings.data = JSON.stringify(params);
        settings.success = function (data) {
            if (data.result === true) {
                $("#resetPwdForm").show();
                $("#inputAuthCodeForm").hide();
            } else {
                alert("인증번호가 일치하지 않습니다.");
                return;
            }
        };
        gnAjaxPost(settings)
    })

    $('#resetNewPwd').on('click', function () {

        if ($("#inputNewPwd").val() !== $("#inputNewPwdConfirm").val()) {
            alert("비밀번호가 일치하지 않습니다.");
            return;
        }

        if (window.confirm("비밀번호를 변경하시겠습니까?")) {
            let params = {};
            params.newPwd = $("#inputNewPwd").val();
            params.newPwdConfirm = $("#inputNewPwdConfirm").val();
            let settings = {};
            settings.url = "/auth/forgotPwd/resetNewPwd";
            settings.async = true;
            settings.data = JSON.stringify(params);
            settings.success = function (data) {
                alert("비밀번호가 변경되었습니다.");
                location.href = "${pageContext.request.contextPath}/login";
            };
            gnAjaxPost(settings)
        }
    })
</script>
</body>

</html>