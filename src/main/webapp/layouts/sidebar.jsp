<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<div class="sidebar">
  <div class="list-group">
    <ul class="nav flex-column">
      <li class="nav-item ">
        <a class="nav-link" href="Home">
          <i class="fas fa-home"></i> Trang chủ
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="">
          <i class="fas fa-folder"></i> Quản lý Chủ đề
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/Exercise">
          <i class="fas fa-book"></i> Quản lý Bài tập
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/Question">
          <i class="fas fa-question-circle"></i> Quản lý Câu hỏi
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/Answer">
          <i class="fas fa-check-circle"></i> Quản lý Đáp Án
        </a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/User">
          <i class="fas fa-user"></i> Quản lý Người dùng
        </a>
      </li>
    </ul>
  </div>
</div>
