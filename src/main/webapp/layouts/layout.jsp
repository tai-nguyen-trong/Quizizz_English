<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Lấy tham số "currentPage" từ Servlet
    String currentPage = (String) request.getAttribute("currentPage");
    if (currentPage == null) currentPage = "home";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<!-- Include Header -->
<jsp:include page="/layouts/header.jsp" />

<div class="d-flex">
    <!-- Include Sidebar -->
    <jsp:include page="/layouts/sidebar.jsp" />

    <!-- Nội dung chính -->
    <div class="container p-4">
        <jsp:include page="/layouts/content.jsp" />
    </div>
</div>

<!-- Include Footer -->
<jsp:include page="/layouts/footer.jsp" />

</body>
</html>
