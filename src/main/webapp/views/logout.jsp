<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.IOException" %>
<%
    // Hủy toàn bộ session
    session.invalidate();
    // Chuyển hướng về trang login
    response.sendRedirect(request.getContextPath() + "/views/login.jsp");
%>
