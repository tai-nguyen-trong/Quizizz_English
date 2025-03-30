<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%
  String currentPage = request.getParameter("currentPage");
  if (currentPage == null || currentPage.isEmpty()) {
    currentPage = "home";
  }
%>

<div class="sidebar">
  <div class="list-group">
    <ul class="nav flex-column">
      <!-- Trang chủ -->
      <li class="nav-item">
        <a class="nav-link <%= "home".equals(currentPage) ? "active" : "" %>"
           href="<%= request.getContextPath() %>/viewPage?currentPage=home">
          <i class="material-icons">home</i> Trang chủ
        </a>
      </li>

      <!-- Quản lý Chủ đề -->
      <li class="nav-item">
        <a class="nav-link <%= "QuanLyDanhSachChuDe".equals(currentPage) ? "active" : "" %>"
           href="<%= request.getContextPath() %>/viewPage?currentPage=QuanLyDanhSachChuDe">
          <i class="material-icons">folder</i> Quản lý Chủ đề
        </a>
      </li>

      <!-- Quản lý Bài tập -->
      <li class="nav-item">
        <a class="nav-link <%= "QuanLyDanhSachBaiTap".equals(currentPage) ? "active" : "" %>"
           href="<%= request.getContextPath() %>/viewPage?currentPage=QuanLyDanhSachBaiTap">
          <i class="material-icons">book</i> Quản lý Bài tập
        </a>
      </li>

      <!-- Quản lý Câu hỏi -->
      <li class="nav-item">
        <a class="nav-link <%= "QuanLyDanhSachCauHoi".equals(currentPage) ? "active" : "" %>"
           href="<%= request.getContextPath() %>/viewPage?currentPage=QuanLyDanhSachCauHoi">
          <i class="material-icons">help</i> Quản lý Câu hỏi
        </a>
      </li>

      <!-- Quản lý Người dùng -->
      <li class="nav-item">
        <a class="nav-link <%= "QuanLyDanhSachNguoiDung".equals(currentPage) ? "active" : "" %>"
           href="<%= request.getContextPath() %>/viewPage?currentPage=QuanLyDanhSachNguoiDung">
          <i class="material-icons">person</i> Quản lý Người dùng
        </a>
      </li>
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
