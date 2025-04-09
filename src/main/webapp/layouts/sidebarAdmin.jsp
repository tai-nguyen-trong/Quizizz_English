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
        <a class="nav-link  ${currentPage == 'home' ? 'active' : ''}"
           href="<%= request.getContextPath() %>/home">
          <i class="material-icons">home</i> Trang chủ
        </a>
      </li>

      <!-- Quản lý Chủ đề -->
      <li class="nav-item">
        <a class="nav-link ${currentPage == 'QuanLyDanhSachChuDe' ? 'active' : ''}"
           href="<%= request.getContextPath() %>/QuanLyDanhSachChuDe">
          <i class="material-icons">folder</i> Quản lý Chủ đề
        </a>
      </li>

      <!-- Quản lý Bài tập -->
      <li class="nav-item">
        <a class="nav-link ${currentPage == 'QuanLyDanhSachBaiTap' ? 'active' : ''}"
           href="<%= request.getContextPath() %>/QuanLyDanhSachBaiTap">
          <i class="material-icons">book</i> Quản lý Bài tập
        </a>
      </li>

      <!-- Quản lý Câu hỏi -->
      <%--<li class="nav-item">
        <a class="nav-link  ${currentPage == 'QuanLyDanhSachCauHoi' ? 'active' : ''}"
           href="<%= request.getContextPath() %>/QuanLyDanhSachCauHoi">
          <i class="material-icons">help</i> Quản lý Câu hỏi
        </a>
      </li>--%>

      <!-- Quản lý Người dùng -->
      <li class="nav-item">
        <a class="nav-link ${currentPage == 'QuanLyDanhSachNguoiDung' ? 'active' : ''}"
           href="<%= request.getContextPath() %>/QuanLyDanhSachNguoiDung">
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
