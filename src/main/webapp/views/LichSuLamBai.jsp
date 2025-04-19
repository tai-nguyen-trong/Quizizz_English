<%@ page import="com.quizizz.english.quizizz_english.dto.LichSuLamBaiDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<LichSuLamBaiDTO> lichSuLamBaiDTOs = (List<LichSuLamBaiDTO>) request.getAttribute("lichSuLamBais");
%>

<style>
    body {
        font-family: Arial, sans-serif;
    }
    .table thead {
        background-color: #f8f9fa;
    }
    .header-section {
        background-color: #007bff;
        color: white;
        padding: 15px 20px;
        font-weight: bold;
        border-radius: 5px;
        margin-bottom: 20px;
    }
</style>

<div class="container mt-4">
    <div class="header-section">
        <h2 class="mb-0">Lịch sử làm bài</h2>
    </div>

    <div class="table-responsive">
        <table class="table table-striped table-bordered" id="exerciseTable">
            <thead>
            <tr>
                <th>Tên bài tập</th>
                <th>Chủ đề</th>
                <th>Cấp độ</th>
                <th>Điểm</th>
<%--                <th>Thời gian làm bài</th>--%>
                <th style="text-align: center;">Chi tiết</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (lichSuLamBaiDTOs != null && !lichSuLamBaiDTOs.isEmpty()) {
                    for (LichSuLamBaiDTO item : lichSuLamBaiDTOs) {
            %>
            <tr>
                <td><%= item.getTenBaiTap() %></td>
                <td><%= item.getTenChuDe() %></td>
                <td><%= item.getTenCapDo() %></td>
                <td><%= item.getDiem() %></td>
<%--                <td><%= item.getThoiGianLamBai() %> phút</td>--%>
                <td style="text-align: center;">
                    <a href="<%= request.getContextPath() %>/KetQua?idLichSuLamBaiTap=<%= item.getId() %>&idBaiTap=<%=item.getIdBaiTap()%>">Chi tiết</a>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5" style="text-align: center;">Chưa có lịch sử làm bài</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<script>
    $(document).ready(function () {
        <% if (lichSuLamBaiDTOs != null && !lichSuLamBaiDTOs.isEmpty()) { %>
        $('#exerciseTable').DataTable({
            paging: true,
            ordering: true,
            searching: true
        });
        <% } %>
    });
</script>
<%--<script>--%>
<%--    $(document).ready(function () {--%>
<%--        $('#exerciseTable').DataTable({--%>
<%--            paging: true,--%>
<%--            ordering: true,--%>
<%--            searching: true--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>
