<%@ page import="java.util.List" %>
<%@ page import="com.quizizz.english.quizizz_english.model.CapDo" %>
<%@ page import="com.quizizz.english.quizizz_english.model.BaiTap" %>
<%@ page import="com.quizizz.english.quizizz_english.model.ChuDe" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>

<%
    List<CapDo> capDos = (List<CapDo>) request.getAttribute("capDos");
    List<BaiTap> baiTaps = (List<BaiTap>) request.getAttribute("baiTaps");
    List<ChuDe> chuDes = (List<ChuDe>) request.getAttribute("chuDes");
//    int idCapDo = (int) request.getAttribute("idCapDo");
//    int idChuDe = (int) request.getAttribute("idChuDe");
    int idChuDe = request.getAttribute("idChuDe") != null ? (int) request.getAttribute("idChuDe") : 0;
    int idCapDo = request.getAttribute("idCapDo") != null ? (int) request.getAttribute("idCapDo") : 0;
%>

<div class="container mt-4">
    <!-- Phần đầu trang với combobox cấp độ -->
    <div class="row mb-4">
        <div class="col-md-12">
            <div class="d-flex align-items-center">
                <form method="GET" action="BaiTap" class="mb-3">
                    <div class="d-flex align-items-center">
                        <label class="me-2 fw-bold">Chủ đề:</label>
                        <select name="idChuDe" class="form-select me-5" style="width: auto; border: 2px solid #000000;" onchange="this.form.submit();">
                            <option value="0" <%= idChuDe == 0 ? "selected" : "" %>>Tất cả chủ đề</option>
                            <% for (ChuDe chuDe : chuDes) { %>
                            <option value="<%= chuDe.getId() %>" <%= chuDe.getId() == idChuDe ? "selected" : "" %>>
                                <%= chuDe.getTenChuDe() %>
                            </option>
                            <% } %>
                        </select>

                        <!-- Combobox Cấp độ -->
                        <label class="me-2 fw-bold">Cấp độ:</label>
                        <select name="idCapDo" class="form-select" style="width: auto; border: 2px solid #000000;" onchange="this.form.submit();">
                            <option value="0" <%= idCapDo == 0 ? "selected" : "" %>>Tất cả cấp độ</option>
                            <% for (CapDo capDo : capDos) { %>
                            <option value="<%= capDo.getId() %>" <%= capDo.getId() == idCapDo ? "selected" : "" %>>
                                <%= capDo.getTenCapDo() %>
                            </option>
                            <% } %>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="row mb-3">
        <div class="col-md-12 text-center">
            <h1 class="fw-bold">Trắc nghiệm Tiếng Anh</h1>
            <p class="text-muted">
                Làm bài tập trắc nghiệm Tiếng Anh miễn phí.
            </p>
        </div>
    </div>

    <div class="row" id="exercise-list">
        <% for (BaiTap baiTap : baiTaps) { %>
        <div class="col-md-12 mb-4">
            <div class="card border-0 shadow-sm">
                <div class="row g-0">
                    <!-- Phần hình ảnh bên trái -->
                    <div class="col-md-3 bg-light" style="border-radius: 15px 0 0 15px; position: relative;">
                        <div class="d-flex flex-column justify-content-center align-items-center h-100 p-3">
                            <h5 class="text-center mb-3">Mã bài tập: <%= baiTap.getMaBaiTap() %></h5>
                            <div class="py-1 px-3 bg-warning bg-opacity-25 rounded-pill">
                                <span class="fw-bold">Thời gian làm bài: <%= baiTap.getThoiGianLamBai() %></span>
                            </div>
                        </div>
                    </div>

                    <!-- Phần nội dung bên phải -->
                    <div class="col-md-9">
                        <div class="card-body">
                            <h5 class="card-title fw-bold">Tên bài tập: <%= baiTap.getTenBaiTap() %></h5>
                            <a href="<%= request.getContextPath() %>/LamBaiTap?idBaiTap=<%= baiTap.getId() %>" class="btn btn-outline-success btn-sm px-3">Làm bài</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>