<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Lấy giá trị currentPage từ request
  String currentPage = (String) request.getAttribute("currentPage");

  // Kiểm tra nếu null hoặc rỗng thì mặc định về "home"
  if (currentPage == null || currentPage.isEmpty() || currentPage.equals("Home")) {
    currentPage = "home";
  } else if (currentPage.equals("QuanLyDanhSachChuDe")) {
    currentPage = "QuanLyDanhSachChuDe";
  }

  // Xây dựng đường dẫn tới trang cần include
  String pagePath = "/views/" + currentPage + ".jsp";
%>

<!-- Kiểm tra file có tồn tại không trước khi include -->
<jsp:include page="<%= pagePath %>" flush="true"/>
