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
  <div class="d-flex justify-content-between align-items-center bg-light p-4 rounded shadow-sm mb-3">
    <h2 class="mb-0">Bài tập</h2>
    <button class="btn btn-success text-white fw-bold">Thêm bài tập mới</button>
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
      <select id="filter-level-1" class="form-select">
        <option value="">Chọn cấp độ</option>
        <option value="Dễ">Dễ</option>
        <option value="Trung bình">Trung bình</option>
        <option value="Khó">Khó</option>
      </select>
    </div>

  </div>


  <!-- Bảng danh sách bài tập -->
  <div class="table-responsive">
    <table class="table table-striped table-bordered" id="exerciseTable" style="width: 100%">
      <thead>
      <tr>
        <th>Id</th>
        <th>Mã bài tập</th>
        <th>Tên bài tập</th>
        <th>Chủ đề</th>
        <th>Cấp độ</th>
        <th>Thời gian làm bài</th>
        <th style="text-align: center;">Chỉnh sửa</th>
        <th style="text-align: center;">Danh sách câu hỏi</th>
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
      "lengthMenu": [5, 10, 20, 50],
      "pagingType": "full_numbers", // Kiểu phân trang đầy đủ
      "language": {
        "processing": "Đang xử lý ..."
      },
      "ajax":
              {
                "url": "<%= request.getContextPath() %>/BaiTap",
                "type": "GET",
                "dataType": "JSON",
                "dataSrc": ""
              },
      "aoColumns": [
        { "mDataProp": "ID" },
        { "mDataProp": "MaBaiTap" },
        { "mDataProp": "TenBaiTap" },
        { "mDataProp": "ChuDe" },
        { "mDataProp": "CapDo" },
        { "mDataProp": "ThoiGianLamBai" },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center'>" +
                    "<i class='material-icons'>edit</i>" +
                    "</button></div>";
          },
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center'>" +
                    "<i class='material-icons'>visibility</i>" +
                    "</button></div>";
          },
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center'>" +
                    "<i class='material-icons'>delete</i>" +
                    "</button></div>";
          },
        },

      ]
    });

    // Bộ lọc theo chủ đề
    $('#filter-topic').on('change', function () {

    });

    // Bộ lọc theo cấp độ
    $('#filter-level').on('change', function () {
    });
  });
</script>
