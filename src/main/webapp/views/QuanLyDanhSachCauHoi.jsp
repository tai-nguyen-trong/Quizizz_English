<%--
  Created by IntelliJ IDEA.
  User: HaiDaiPC
  Date: 3/26/2025
  Time: 10:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
  body {
    font-family: Arial, sans-serif;
  }
  .table thead {
    background-color: #f8f9fa;
  }
  .filter-group {
    display: flex;
    gap: 10px;
    align-items: center;
  }
  .dataTables_filter {
    float: right !important;
    text-align: right;
  }
  .header-section {
    background-color: #007bff;  /* Màu xanh */
    color: white;
    padding: 15px 20px;
    font-weight: bold;
    border-radius: 5px;
    margin-bottom: 20px;
    display: flex;
    align-items: center;
  }

  .header-section button {
    background-color: white;
    color: #007bff;
    border: none;
    padding: 8px 15px;
    font-size: 16px;
    border-radius: 5px;
    font-weight: bold;
  }

  .header-section button:hover {
    background-color: #e0e0e0;
  }
</style>
<div class="container mt-4">
  <div class="header-section d-flex justify-content-between align-items-center">
    <h2 class="mb-0">DANH SÁCH CÂU HỎI</h2>
    <button class="btn btn-secondary text-dark fw-bold">Thêm câu hỏi mới</button>
  </div>
  <!-- Thanh tìm kiếm và bộ lọc -->
  <div class="row clearfix mb-3">
    <div class="col-md-3">
      <select id="filter-topic" class="form-select">
        <option value="">Chọn chủ đề</option>
        <option value="Động vật">Động vật</option>
        <option value="Màu sắc">Màu sắc</option>
        <option value="Công nghệ">Công nghệ</option>
      </select>
    </div>

    <div class="col-md-3">
      <select id="filter-level-2" class="form-select">
        <option value="">Chọn mã bài ập</option>
      </select>
    </div>


  </div>


  <!-- Bảng danh sách bài tập -->
  <div class="table-responsive">
    <table class="table table-striped table-bordered" id="exerciseTable" style="width: 100%">
      <thead>
      <tr>
        <th>Id</th>
        <th>Mã câu hỏi</th>
        <th>Tên câu hỏi</th>
        <th>Mã bài tập</th>
        <th>Tên chủ đề</th>
        <th style="text-align: center;">Chỉnh sửa</th>
        <th style="text-align: center;">Danh sách câu trả lời</th>
        <th style="text-align: center;">Xóa</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>

<script>
  $(document).ready(function () {
    // Kích hoạt DataTables
    const table = $('#exerciseTable').DataTable({
      "paging": true,
      "searching": true,
      "ordering": true,
      "info": true,
      "lengthMenu": [5, 10, 20, 50]
    });

    // Bộ lọc theo chủ đề
    $('#filter-topic').on('change', function () {

    });

    // Bộ lọc theo cấp độ
    $('#filter-level').on('change', function () {
    });
  });
</script>
