<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>

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

<div class="card p-4 shadow-sm bg-white rounded">
    <h4 class="mb-4 fw-bold">Thông tin cá nhân</h4>
    <form method="post" action="ThongTinCaNhan" id="updateForm">
<%--        <div class="row mb-3">--%>
<%--            <div class="col-md-6">--%>
<%--                <label class="form-label fw-semibold">ID<span class="text-danger">*</span></label>--%>
                <input type="hidden" type="text" class="form-control" value="<%= user.getId() %>"  name="id"> <!--readonly -->
<%--            </div>--%>
<%--            <div class="col-md-6">--%>
<%--                <label class="form-label fw-semibold">Mật khẩu<span class="text-danger">*</span></label>--%>
                <input type="hidden" type="text" class="form-control" value="<%= user.getMatKhau() %>"  name="matKhau"> <!--readonly -->
<%--            </div>--%>
<%--        </div>--%>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label fw-semibold">Họ và tên<span class="text-danger">*</span></label>
                <input type="text" class="form-control" value="<%= user.getHoVaTen() %>"  name="hoVaTen"> <!--readonly -->
            </div>
            <div class="col-md-6">
                <label class="form-label fw-semibold">Tuổi<span class="text-danger">*</span></label>
                <input type="text" class="form-control" value="<%= user.getTuoi() %>"  name="tuoi"> <!--readonly -->
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label fw-semibold">Email<span class="text-danger">*</span></label>
                <input type="email" class="form-control" value="<%= user.getEmail() %>" name="email"> <!--readonly -->
            </div>
            <div class="col-md-6">
                <label class="form-label fw-semibold">Số điện thoại</label>
                <input type="text" class="form-control" value="<%= user.getSoDienThoai() %>" name="soDienThoai"> <!--readonly -->
            </div>
        </div>

<%--        <div class="row mb-3">--%>
<%--            <div class="col-md-6">--%>
<%--                <label class="form-label fw-semibold">Quốc gia<span class="text-danger">*</span></label>--%>
<%--                <select class="form-select" disabled>--%>
<%--                    <option selected>Việt Nam</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--            <div class="col-md-6">--%>
<%--                <label class="form-label fw-semibold">Tỉnh thành</label>--%>
<%--                <select class="form-select" disabled>--%>
<%--                    <option selected>Hồ Chí Minh</option>--%>
<%--                </select>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div class="mb-4">--%>
<%--            <label class="form-label fw-semibold">Nghề nghiệp</label>--%>
<%--            <input type="text" class="form-control" value="Sinh viên" readonly>--%>
<%--        </div>--%>

        <div class="text-end">
            <button type="button" class="btn btn-primary px-4 rounded-pill" onclick="openConfirmDialog()">Sửa</button>
        </div>
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
    function openConfirmDialog() {
        var myModal = new bootstrap.Modal(document.getElementById('confirmUpdateModal'), {});
        myModal.show();
    }

    document.addEventListener("DOMContentLoaded", function () {
        document.getElementById("confirmBtn").addEventListener("click", function () {
            document.getElementById("updateForm").submit();
        });
    });
</script>

