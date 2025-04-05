<%@ page import="com.quizizz.english.quizizz_english.model.ChuDe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quizizz.english.quizizz_english.model.CapDo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  List<ChuDe> chudes = (List<ChuDe>) request.getAttribute("chuDes");
  List<CapDo> capDos = (List<CapDo>) request.getAttribute("capDos");
%>
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
      <select id="timkiem-chuDe" class="form-select" data-live-search="true">
        <option value="">Chọn chủ đề</option>
        <% for (ChuDe item : chudes) { %>
        <option value="<%= item.getId() %>">
          <%= item.getTenChuDe() %>
        </option>
        <% } %>
      </select>
    </div>

    <div class="col-md-3">
      <select id="timkiem-capDo" class="form-select" data-live-search="true">
        <option value="">Chọn cấp độ</option>
        <% for (CapDo item : capDos) { %>
        <option value="<%= item.getId() %>">
          <%= item.getTenCapDo() %>
        </option>
        <% } %>
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
<!-- Modal Thêm bài tập  -->
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
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Thời Gian Làm Bài</label>
              <input id="thoigianlambai" type="text" class="form-control">
            </div>

            <!-- Chọn Nhà Sản Xuất -->
            <div class="col-md-6 mt-3">
              <label class="fw-bold"  for="otp-chuDe">Chủ đề</label>
              <select id="otp-chuDe" class="form-select" data-live-search="true">
                <option value="">Chọn chủ đề</option>
                <% for (ChuDe item : chudes) { %>
                <option value="<%= item.getId() %>">
                  <%= item.getTenChuDe() %>
                </option>
                <% } %>
              </select>
            </div>
            <div class="col-md-6 mt-3">
              <label class="fw-bold"  for="otp-capDo">Chọn cấp độ</label>
              <select id="otp-capDo" class="form-select" data-live-search="true">
                <option value="">Chọn cấp độ</option>
                <% for (CapDo item : capDos) { %>
                <option value="<%= item.getId() %>">
                  <%= item.getTenCapDo() %>
                </option>
                <% } %>
              </select>
            </div>
          </div>
        </div>
      </div>

      <!-- Footer -->
      <div class="modal-footer">
        <button id="btn-themThongTin" type="button" class="btn btn-success">Thêm Bài Tập</button>
        <button id="btn-luuThongTin" type="button" class="btn btn-success">Lưu thông tin</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>


<script>
  $(document).ready(function () {
    //biến
    let idBaiTap = 0;
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
                "url": "<%= request.getContextPath() %>/DanhSachBaiTap",
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
        { "mDataProp": "thoiGianLamBai" ,type: "num"},
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
      $("#btn-luuThongTin").hide();
      $("#btn-themThongTin").show();
    });
    // Sự kiện Click vào Nút Chỉnh Sửa
    $('#exerciseTable tbody').on('click', '.btnEdit', function () {
      let rowData = table.row($(this).parents('tr')).data(); // Lấy dữ liệu hàng
      idBaiTap = rowData.id;
      $("#modalThemBaiTap").modal("show");
      $("#btn-themThongTin").hide();
      $("#btn-luuThongTin").show();
      $("#tenBaiTap").val(rowData.tenBaiTap);
      $("#thoigianlambai").val(rowData.thoiGianLamBai);
      $("#otp-chuDe").val(rowData.idChuDe).change();
      $("#otp-capDo").val(rowData.idCapDo).change();

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
      idBaiTap = rowData.id;
      if (confirm("Bạn có chắc chắn muốn xóa bài tập: " + rowData.tenBaiTap + " không?")) {
        // Gửi Ajax Request để xóa trên server
        $.ajax({
          url: "<%= request.getContextPath() %>/XoaBaiTap?id=" + idBaiTap,
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
    // thêm bài tập
    $('#btn-themThongTin').on('click',function (){
      $.LoadingOverlay("show");
      var tenBaiTap = $("#tenBaiTap").val();
      var thoiGianLamBai = $("#thoigianlambai").val();
      var idCapDo = $("#otp-capDo").val();
      var idChuDe = $("#otp-chuDe").val();
      $.ajax({
        url: "<%= request.getContextPath() %>/QuanLyDanhSachBaiTap",
        type: "POST",
        data: {
          tenBaiTap: tenBaiTap,
          thoiGianLamBai: thoiGianLamBai,
          idCapDo: idCapDo,
          idChuDe: idChuDe,
        },
        success: function (response) {
          console.log(response);
          $("#modalThemBaiTap").modal("hide");
          table.ajax.reload(null, false);
          $.LoadingOverlay("hide");
        },
        error: function () {
          console.log("them that bai");
          $.LoadingOverlay("hide");
        }
      });
    });

    // sửa bài tập
    $('#btn-luuThongTin').on('click',function (){
      $.LoadingOverlay("show");
      var tenBaiTap = $("#tenBaiTap").val();
      var thoiGianLamBai = $("#thoigianlambai").val();
      var idCapDo = $("#otp-capDo").val();
      var idChuDe = $("#otp-chuDe").val();
      $.ajax({
        url: "<%= request.getContextPath() %>/CapNhatBaiTap",
        type: "PUT",
        data:  JSON.stringify({
          id:idBaiTap,
          tenBaiTap: tenBaiTap,
          thoiGianLamBai: thoiGianLamBai,
          idCapDo: idCapDo,
          idChuDe: idChuDe
        }),
        success: function (response) {
          console.log(response);
          $("#modalThemBaiTap").modal("hide");
          table.ajax.reload(null, false);
          $.LoadingOverlay("hide");
        },
        error: function () {
          console.log("them that bai");
          $.LoadingOverlay("hide");
        }
      });
    });
  });
</script>
