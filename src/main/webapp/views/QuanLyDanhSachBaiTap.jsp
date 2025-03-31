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
    <button id="btn-ThemBaiTap" class="btn btn-success text-white fw-bold">Thêm bài tập mới</button>
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
<!-- Modal Thêm Chủ Đề -->
<div class="modal fade" id="modalThemBaiTap" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 70%;">
    <div class="modal-content">
      <!-- Header -->
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="modalLabel">Thêm Bài Tập Mới</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Body -->
      <div class="modal-body">
        <div class="container">
          <div class="row">
            <!-- Tên Bài Tập -->
            <div class="col-md-12">
              <label class="fw-bold">Tên Bài Tập</label>
              <input id="tenBaiTap" type="text" class="form-control">
            </div>

            <!-- Mã Bài Tập & Thời Gian Làm Bài -->
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Mã Bài Tập</label>
              <input id="maBaiTap" type="text" class="form-control">
            </div>
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Thời Gian Làm Bài</label>
              <input id="thoigianlambai" type="text" class="form-control">
            </div>

            <!-- Chọn Nhà Sản Xuất -->
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Chủ đề</label>
              <select id="filter-nsx" class="form-select">
                <option value="">Chọn chủ đề</option>
                <option value="NSX1">Nhà Sản Xuất 1</option>
                <option value="NSX2">Nhà Sản Xuất 2</option>
                <option value="NSX3">Nhà Sản Xuất 3</option>
              </select>
            </div>
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Chủ đề</label>
              <select id="select-capdo" class="form-select">
                <option value="">Chọn chủ đề</option>
                <option value="NSX1">Nhà Sản Xuất 1</option>
                <option value="NSX2">Nhà Sản Xuất 2</option>
                <option value="NSX3">Nhà Sản Xuất 3</option>
              </select>
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
      "ordering": true,
      "filter": true,
      "destroy": true,
      "orderMulti": false,
      "serverSide": true,
      "processing": true,
      "bLengthChange": false,
      "draw": true,
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
        { "mDataProp": "id" },
        { "mDataProp": "maBaiTap" },
        { "mDataProp": "tenBaiTap" },
        { "mDataProp": "tenChuDe" },
        { "mDataProp": "tenCapDo" },
        { "mDataProp": "thoiGianLamBai" },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center btnEdit'>" +
                    "<i class='material-icons'>edit</i>" +
                    "</button></div>";
          },
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center btnDetails'>" +
                    "<i class='material-icons'>details</i>" +
                    "</button></div>";
          },
        },
        {
          "data": null,
          "render": function (data, type, full, meta) {
            return "<div class='d-flex justify-content-center align-items-center'>" +
                    "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center btnDelete'>" +
                    "<i class='material-icons'>delete</i>" +
                    "</button></div>";
          },
        },

      ]
    });


    // Xử lý sự kiện khi nhấn nút "Thêm chủ đề"
    $("#btn-ThemBaiTap").click(function () {
      $("#modalThemBaiTap").modal("show");
    });
    // Sự kiện Click vào Nút Chỉnh Sửa
    $('#exerciseTable tbody').on('click', '.btnEdit', function () {
      let rowData = table.row($(this).parents('tr')).data(); // Lấy dữ liệu hàng
      alert("Chỉnh sửa bài tập:\n" +
              "ID: " + rowData.ID + "\n" +
              "Tên bài tập: " + rowData.TenBaiTap + "\n" +
              "Chủ đề: " + rowData.ChuDe);

      // Hiển thị Modal chỉnh sửa ở đây
      // Ví dụ: $('#modalEdit').modal('show');
    });

    // Sự kiện Click vào Nút Xem Chi Tiết
    $('#exerciseTable tbody').on('click', '.btnDetails', function () {
      let rowData = table.row($(this).parents('tr')).data();
      alert("Xem chi tiết bài tập:\n" +
              "Mã bài tập: " + rowData.MaBaiTap + "\n" +
              "Mô tả: " + rowData.ThoiGianLamBai);
    });

    // Sự kiện Click vào Nút Xóa
    $('#exerciseTable tbody').on('click', '.btnDelete', function () {
      let rowData = table.row($(this).parents('tr')).data();
      if (confirm("Bạn có chắc chắn muốn xóa bài tập: " + rowData.TenBaiTap + " không?")) {
        // Gửi Ajax Request để xóa trên server
        $.ajax({
          url: "<%= request.getContextPath() %>/BaiTap?id=" + rowData.ID,
          type: "DELETE",
          success: function (response) {
            alert("Đã xóa thành công!");
            table.ajax.reload(); // Tải lại bảng sau khi xóa
          },
          error: function () {
            alert("Xóa thất bại!");
          }
        });
      }
    });
  });
</script>
