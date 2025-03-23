<%--
  Created by IntelliJ IDEA.
  User: HaiDaiPC
  Date: 3/22/2025
  Time: 9:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Đăng nhập</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/fontawesome-free-6.7.2-web/css/all.min.css">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f8f9fa;
        }
        .signup-container {
            width: 400px;
            padding: 20px;
            background: white;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .eye-icon {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="signup-container text-center">
    <h2>Đăng nhập</h2>
    <p> Hoặc<a href="./register.jsp"> Đăng kí</a></p>
        <div class="mb-2">
            <input type="email" class="form-control" placeholder="demo@gmail.com">
        </div>
        <div class="mb-2 position-relative">
            <input type="password" class="form-control" id="password" placeholder="Mật khẩu">
            <span class="position-absolute top-50 end-0 translate-middle-y me-2 eye-icon">
                    <i class="fa-solid fa-eye" id="eye-icon1"></i>
                </span>
        </div>
        <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
</div>

</body>
</html>
<script src="${pageContext.request.contextPath}/assets/jquery.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.bundle.js"></script>
<script src="${pageContext.request.contextPath}/assets/fontawesome-free-6.7.2-web/js/all.min.js" crossorigin="anonymous"></script>
<script>
    $(document).ready(function() {
        $(".eye-icon i").click(function() {
            let input = $(this).closest(".position-relative").find("input");
            if (input.attr("type") === "password") {
                input.attr("type", "text");
                $(this).removeClass("fa-eye").addClass("fa-eye-slash");
            } else {
                input.attr("type", "password");
                $(this).removeClass("fa-eye-slash").addClass("fa-eye");
            }
        });
    });
</script>