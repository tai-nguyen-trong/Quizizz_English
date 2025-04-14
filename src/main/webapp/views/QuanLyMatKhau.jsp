<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.quizizz.english.quizizz_english.model.NguoiDung" %>
<%@ page session="true" %>
<%
    //  kiểm tra người dùng đã đăng nhập hay chưa
    NguoiDung user = (NguoiDung) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("/layouts/login.jsp");
        return;
    }
%>

<div class="container" style="max-width: 600px; margin: 0 auto; background: white; padding: 40px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
    <div class="text-center mb-4">
        <img src="https://cdn-icons-png.flaticon.com/512/3064/3064197.png" alt="lock" width="100" height="100"/>
    </div>

    <form method="post" action="QuanLyMatKhau" id="updateFormMatKhau">

        <input type="hidden" type="text" class="form-control" value="<%= user.getId() %>"  name="id">

        <div class="mb-3">
            <label for="oldPassword" class="form-label">Mật khẩu cũ</label>
            <div class="input-group">
                <input type="password" class="form-control" id="oldPassword" name="matKhauCu" required>
                <span class="input-group-text">
                    <i class="material-icons" style="cursor: pointer;">visibility</i>
                </span>
            </div>
        </div>

        <div class="mb-3">
            <label for="newPassword" class="form-label">Mật khẩu mới</label>
            <div class="input-group">
                <input type="password" class="form-control" id="newPassword" name="matKhauMoi" required>
                <span class="input-group-text">
                    <i class="material-icons" style="cursor: pointer;">visibility</i>
                </span>
            </div>
        </div>

        <div class="mb-3">
            <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới</label>
            <div class="input-group">
                <input type="password" class="form-control" id="confirmPassword" name="xacNhanMatKhauMoi" required>
                <span class="input-group-text">
                    <i class="material-icons" style="cursor: pointer;">visibility</i>
                </span>
            </div>
        </div>

<%--        <ul class="text-muted mb-4">--%>
<%--            <li>✔ Mật khẩu trong khoảng 8–50 ký tự</li>--%>
<%--            <li>✔ Mật khẩu không được trùng số điện thoại/username</li>--%>
<%--            <li>✔ Mật khẩu phải có ít nhất 1 chữ thường, 1 chữ hoa và 1 số</li>--%>
<%--        </ul>--%>

        <button type="button" class="btn btn-primary w-100" onclick="openConfirmDialog()">Lưu</button>
    </form>
</div>



<!-- Modal Xác nhận -->
<div class="modal fade" id="confirmUpdateModal" tabindex="-1" role="dialog" aria-labelledby="confirmLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmLabel">Xác nhận</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Đóng"></button>
            </div>
            <div class="modal-body">
                Bạn có chắc chắn muốn cập nhật thông tin không?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-success" id="confirmBtn">✔ Đồng ý</button>
                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">✖ Hủy</button>
            </div>
        </div>
    </div>
</div>

<script>
    // Open modal
    function openConfirmDialog() {
        var myModal = new bootstrap.Modal(document.getElementById('confirmUpdateModal'), {});
        myModal.show();
    }

    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("confirmBtn").addEventListener("click", function () {
            document.getElementById("updateFormMatKhau").submit();
        });
    });

    // Toggle visibility
    const toggleIcons = document.querySelectorAll('.material-icons');
    toggleIcons.forEach(icon => {
        icon.addEventListener('click', () => {
            const input = icon.parentElement.previousElementSibling;
            if (input.type === "password") {
                input.type = "text";
                icon.textContent = "visibility_off";
            } else {
                input.type = "password";
                icon.textContent = "visibility";
            }
        });
    });
</script>
