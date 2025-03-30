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
  <div class="d-flex justify-content-between align-items-center bg-light p-4 rounded shadow-sm mb-3" >
    <h2 class="mb-0">DANH SÁCH CÂU HỎI</h2>
    <button id="btn-ThemCauHoi" class="btn btn-success text-white fw-bold">Thêm câu hỏi mới</button>
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

<!-- Modal Thêm Câu hỏi -->
<div class="modal fade" id="modalThemCauHoi" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 70%;">
    <div class="modal-content">
      <!-- Header -->
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="modalLabel">Thêm Câu Hỏi Mới</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Body -->
      <div class="modal-body">
        <div class="container">
          <div class="row clearfix">
            <!-- Tên Bài Tập -->
            <div class="col-md-12">
              <label class="fw-bold">Tên Câu Hỏi</label>
              <input id="tenCauHoi" type="text" class="form-control">
            </div>
            <!-- Chọn Nhà Sản Xuất -->
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Mã Bài Tập</label>
              <select id="filter-nsx" class="form-select">
                <option value="">Chọn bài tập</option>
                <option value="NSX1">Nhà Sản Xuất 1</option>
                <option value="NSX2">Nhà Sản Xuất 2</option>
                <option value="NSX3">Nhà Sản Xuất 3</option>
              </select>
            </div>
          </div>
          <div class="row clearfix">
            <!-- Tên Bài Tập -->
            <div class="col-md-12">
              <label class="fw-bold">Tên đáp án</label>
              <input id="tenDapAn" type="text" class="form-control">
            </div>

            <div class="col-md-6 mt-3">
              <label class="fw-bold">Đáp Án Đúng</label>
              <div class="form-check">
                <input id="dapandung" type="checkbox" class="form-check-input">
                <label for="dapandung" class="form-check-label">Đúng</label>
              </div>
            </div>
            <div class="col-md-6 mt-3">
              <button type="button" class="btn btn-success">Thêm đáp án</button>
            </div>
          </div>
          <div class="row clearfix mt-3">
            <div class="table-responsive">
              <table class="table table-striped table-bordered" id="table-dapan" style="width: 100%">
                <thead>
                <tr>
                  <th style="text-align:center">Stt</th>
                  <th style="text-align:center">Tên đáp án</th>
                  <th style="text-align:center">Đáp án đúng</th>
                  <th style="text-align:center">Xóa</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>

      <!-- Footer -->
      <div class="modal-footer">
        <button type="button" class="btn btn-success">Thêm Bài Tập</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
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
    var tableDapAn = $("#table-dapan").DataTable(
            {
              "processing": true,
              dom: 'Bfrtip',
              columnDefs: [
              ],
              buttons: [
                {
                  extend: 'pdfHtml5',
                  title: 'Chi tiết đề nghị thanh toán'
                },
                {
                  extend: 'excelHtml5',
                  title: 'Chi tiết đề nghị thanh toán'
                },
                'print'
              ]
            }
    );

    // Bộ lọc theo chủ đề
    $("#btn-ThemCauHoi").click(function () {
      $("#modalThemCauHoi").modal("show");
    });
  });
</script>
