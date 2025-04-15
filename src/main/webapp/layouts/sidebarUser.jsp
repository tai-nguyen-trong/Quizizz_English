<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%
    String currentPage = request.getParameter("currentPage");
    if (currentPage == null || currentPage.isEmpty()) {
        currentPage = "home";
    }
%>

<!-- checkLogin -->
<%
    Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
    if (isLoggedIn == null) {
        isLoggedIn = false;
    }
%>

<div class="sidebar">
    <div class="list-group">
        <ul class="nav flex-column">
            <!-- Trang chủ -->
            <li class="nav-item">
                <a class="nav-link  ${currentPage == 'home' ? 'active' : ''}"
                   href="<%= request.getContextPath() %>/home/user">
                    <i class="material-icons">home</i> Trang chủ
                </a>
            </li>

            <% if (isLoggedIn) { %>
            <!-- Thông tin cá nhân -->
            <li class="nav-item">
                <a class="nav-link ${currentPage == 'ThongTinCaNhan' ? 'active' : ''}"
                   href="<%= request.getContextPath() %>/ThongTinCaNhan">
                    <i class="material-icons">folder</i> Thông tin cá nhân
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link ${currentPage == 'QuanLyMatKhau' ? 'active' : ''}"
                   href="<%= request.getContextPath() %>/QuanLyMatKhau">
                    <i class="material-icons">folder</i> Quản lý mật khẩu
                </a>
            </li>

            <!-- Lịch sử làm bài -->
            <li class="nav-item">
                <a class="nav-link ${currentPage == 'LichSuLamBai' ? 'active' : ''}"
                   href="<%= request.getContextPath() %>/LichSuLamBai">
                    <i class="material-icons">book</i> Lịch sử làm bài
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link ${currentPage == 'LamBaiTap' ? 'active' : ''}"
                   href="<%= request.getContextPath() %>/LamBaiTap">
                    <i class="material-icons">book</i> LamBaiTap
                </a>
            </li>


            <% } %>
        </ul>
    </div>
</div>

<!-- Link Material Icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

<!-- CSS để tô màu menu được chọn -->
<style>
    .nav-link {
        color: #333;
        font-weight: bold;
        padding: 10px 15px;
    }

    .nav-link i {
        margin-right: 10px;
        vertical-align: middle;
    }

    /* Khi menu được chọn */
    .nav-link.active {
        background-color: #007bff; /* Màu xanh Bootstrap */
        color: white !important;
        border-radius: 5px;
    }
</style>
