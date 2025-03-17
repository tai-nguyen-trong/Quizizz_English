<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý người dùng</title>

    <!-- Bootstrap CSS (Nếu sử dụng Bootstrap, tải về và đặt vào /resources/css) -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css">

    <!-- DataTables CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/DataTable/datatables.min.css">
</head>
<body>

<div class="container mt-4">
    <h2>Danh sách người dùng</h2>
    <table id="userTable" class="display table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Email</th>
            <th>Vai trò</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>Nguyễn Văn A</td>
            <td>vana@example.com</td>
            <td>Admin</td>
        </tr>
        <tr>
            <td>2</td>
            <td>Trần Thị B</td>
            <td>thib@example.com</td>
            <td>Người dùng</td>
        </tr>
        </tbody>
    </table>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/assets/jquery.js"></script>
<script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js"></script>

<!-- DataTables JS -->
<script src="${pageContext.request.contextPath}/assets/DataTable/datatables.min.js"></script>

<script>
    $(document).ready(function () {
        $('#userTable').DataTable({
            "paging": true,
            "searching": true,
            "ordering": true,
            "info": true,
            "lengthMenu": [5, 10, 25, 50],
            "language": {
                "lengthMenu": "Hiển thị _MENU_ dòng",
                "zeroRecords": "Không tìm thấy dữ liệu",
                "info": "Hiển thị _START_ đến _END_ của _TOTAL_ dòng",
                "infoEmpty": "Không có dữ liệu",
                "search": "Tìm kiếm:",
                "paginate": {
                    "first": "Đầu",
                    "last": "Cuối",
                    "next": "Tiếp",
                    "previous": "Trước"
                }
            }
        });
    });
</script>

</body>
</html>
