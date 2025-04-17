<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>403 - Truy cập bị từ chối</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background-color: #f2f2f2;
            text-align: center;
            padding-top: 100px;
        }
        .error-container {
            background-color: white;
            display: inline-block;
            padding: 40px 60px;
            border-radius: 12px;
            box-shadow: 0 0 12px rgba(0,0,0,0.1);
        }
        h1 {
            font-size: 48px;
            color: #e74c3c;
        }
        p {
            font-size: 18px;
            color: #333;
        }
        a {
            display: inline-block;
            margin-top: 20px;
            text-decoration: none;
            padding: 10px 20px;
            background-color: #3498db;
            color: white;
            border-radius: 6px;
            transition: background-color 0.3s ease;
        }
        a:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
<div class="error-container">
    <h1>403</h1>
    <p>Bạn không có quyền truy cập vào trang này.</p>
    <a href="<%= request.getContextPath() %>/home">Về trang chủ</a>
</div>
</body>
</html>
