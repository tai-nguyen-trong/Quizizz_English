<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // Lấy giá trị của tham số "currentPage" từ Servlet
  String currentPage = (String) request.getAttribute("currentPage");
  if (currentPage == null) currentPage = "home";
%>

<jsp:include page="/views/<%= currentPage + \".jsp\" %>" flush="true" />
