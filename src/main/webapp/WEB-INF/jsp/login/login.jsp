<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>login</title>
</head>
<body>
<h1>로그인 페이지 입니다.</h1>
<form id="frmLogin" action="/login" method="post">
    <input type="text" id="username" name="username" placeholder="아이디" form="frmLogin"><br>
    <input type="password" id="password" name="password" placeholder="비밀번호" form="frmLogin"><br>
</form>
<button type="submit" id="submitBtn">로그인</button>
<%--<script src="${pageContext.request.contextPath}/js/api/jquery/jquery-3.6.1.min.js"></script>--%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
