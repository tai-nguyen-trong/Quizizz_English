<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Lấy tham số "currentPage" từ Servlet
    String currentPage = (String) request.getAttribute("currentPage");

    if (currentPage == null || currentPage.isEmpty()) {
        currentPage = "home";
    } else {
        switch (currentPage) {
            case "login":
                currentPage = "login";
                break;
            case "register":
                currentPage = "register";
                break;
            case "ThongTinCaNhan":
                currentPage = "ThongTinCaNhan";
                break;
            case "QuanLyMatKhau":
                currentPage = "QuanLyMatKhau";
                break;
            case "LichSuLamBai":
                currentPage = "LichSuLamBai";
                break;
            case "LamBaiTap":
                currentPage = "LamBaiTap";
                break;
            case "BaiTap":
                currentPage = "BaiTap";
                break;
            case "KetQua":
                currentPage = "KetQua";
                break;
            default:
                currentPage = "home"; // Giá trị mặc định nếu không khớp case nào
                break;
        }
    }
    String pagePath = "/views/" + currentPage + ".jsp";
%>

<%
    // Kiểm tra nếu người dùng đã đăng nhập
    Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
    if (isLoggedIn == null) {
        isLoggedIn = false;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Dashboard</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/2.2.2/css/dataTables.dataTables.css" />
    <script src="https://cdn.datatables.net/2.2.2/js/dataTables.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/gasparesganga-jquery-loading-overlay@2.1.7/dist/loadingoverlay.min.js"></script>

    <style>
        /* Cấu trúc tổng thể */
        html, body {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
        }

        /* Header cố định trên cùng */
        .header {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            height: 60px;
            background: #3e68ec;
            color: white;
            display: flex;
            align-items: center;
            padding: 0 20px;
            z-index: 1000;
            font-weight: bold;
            font-size: 20px;
        }

        /* Sidebar chiếm full chiều cao */
        .sidebar {
            width: 260px;
            height: 100vh;
            background: white;
            border-right: 1px solid #ddd;
            padding: 15px;
            position: fixed;
            left: 0;
            top: 60px;
            bottom: 0;
            overflow-y: auto;
        }

        /* Nội dung chính */
        .content {
            margin-left: 260px;
            margin-top: 60px; /* Đẩy xuống dưới header */
            flex: 1;
            padding: 20px;
            background: #f5f5f5;
            min-height: calc(100vh - 60px);
            overflow-y: auto;
        }

    </style>
</head>
<body>
<!-- Header -->
<div class="header">
    <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
        <div style="font-size: 24px; font-weight: bold;">Quizizz English</div>

        <div>
            <% if (!isLoggedIn) { %>
            <a href="<%= request.getContextPath() %>/login"
               style="color: white; text-decoration: none; font-weight: bold; font-size: 16px;">
                Đăng nhập
            </a>
            <a> - </a>
            <a href="<%= request.getContextPath() %>/register"
               style="color: white; text-decoration: none; font-weight: bold; font-size: 16px;">
                Đăng ký
            </a>
            <% } else { %>
            <a href="<%= request.getContextPath() %>/logout"
               style="color: white; text-decoration: none; font-weight: bold; font-size: 16px;">
                Đăng xuất
            </a>
            <% } %>
        </div>
    </div>
</div>
<div class="sidebar">
    <jsp:include page="/layouts/sidebarUser.jsp"/>
</div>

<!-- Wrapper -->
<div class="wrapper">
    <!-- Sidebar -->


    <!-- Nội dung chính -->
        <div class="content">
            <jsp:include page="<%= pagePath %>" flush="true"/>
        </div>
</div>
</body>
</html>



