<%@ page import="com.quizizz.english.quizizz_english.model.ChuDe" %>
<%@ page import="com.quizizz.english.quizizz_english.model.CapDo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quizizz.english.quizizz_english.model.BaiTap" %>


<%
  BaiTap baiTap = (BaiTap) request.getAttribute("baiTap");
  String idBaiTap = (String) request.getAttribute("idBaiTap");
%>
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
      <select id="timkiem-BaiTap" class="form-select" data-live-search="true">
        <option value="<%= baiTap.getId() %>"><%= baiTap.getMaBaiTap() %></option>

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
        <th>Mã câu hỏi</th>
        <th>Tên câu hỏi</th>
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
            <div class="col-md-6">
              <label class="fw-bold">Tên Câu Hỏi</label>
              <input id="tenCauHoi" type="text" class="form-control">
            </div>
            <!-- Chọn Nhà Sản Xuất -->
            <div class="col-md-6 mt-3">
              <label class="fw-bold">Mã Bài Tập</label>
              <select id="otp-BaiTap" class="form-select" data-live-search="true">
                <option value="<%= baiTap.getId() %>"><%= baiTap.getMaBaiTap() %></option>
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
              <button id="btn-themtam" type="button" class="btn btn-success">Thêm đáp án</button>
            </div>
          </div>
          <div class="row clearfix mt-3">
            <div class="table-responsive">
              <table class="table table-striped table-bordered" id="table-dapan" style="width: 100%">
                <thead>
                <tr>
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
        <button id="btn-luuCauHoi" type="button" class="btn btn-success">Thêm Câu Hỏi</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>

<script>
  $(document).ready(function () {
    let idCauHoi = 0;
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
                "url": "<%= request.getContextPath() %>/QuanLyDanhSachCauHoi",
                'data': function(d) {
                  d.idBaiTap = <%= request.getAttribute("idBaiTap") != null ? request.getAttribute("idBaiTap") : "null" %>;
                },
                "type": "POST",
                "dataType": "JSON",
                "dataSrc":  function (json) {
                  return json.data;
                },

              },
      "order": [[0, "asc"]], // Sắp xếp mặc định theo ID
      "aoColumns": [
        { "mDataProp": "id", orderable: true ,type: "num"},
        { "mDataProp": "maBaiTap", orderable: true },
        { "mDataProp": "tenBaiTap", orderable: true },
        { "mDataProp": "maCauHoi", orderable: true },
        { "mDataProp": "tenCauHoi", orderable: true },
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
    $('#exerciseTable tbody').on('click', '.btnDetails', function () {
      let rowData = table.row($(this).parents('tr')).data();
      idCauHoi = rowData.id;
      $("#modalThemCauHoi").modal("show");
      $("#tenCauHoi").val(rowData.tenCauHoi);
      $("#otp-BaiTap").val(rowData.idBaiTap).change();


    });
    // Bộ lọc theo chủ đề
    $("#btn-ThemCauHoi").click(function () {
      $("#modalThemCauHoi").modal("show");
    });
    $("#btn-themtam").click(function () {
      var tenDapAn = $('#tenDapAn').val().trim();
      var isDapAnDung = $('#dapandung').is(':checked');

      if (tenDapAn === "") {
        alert("Vui lòng nhập tên đáp án.");
        return;
      }

      // Chuyển giá trị checkbox thành chữ
      var hienThiDapAnDung = isDapAnDung ? '✔️ Đúng' : '❌ Sai';
      let btnXoa = "<div class='d-flex justify-content-center align-items-center'>" +
              "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center xoa'>" +
              "<i class='material-icons'>delete</i>" +
              "</button></div>";
      // Thêm dòng mới vào bảng
      tableDapAn.row.add([
        tenDapAn,
        hienThiDapAnDung,
        btnXoa
      ]).draw();
      // Reset lại input
      $('#tenDapAn').val('');
      $('#dapandung').prop('checked', false);
    });
    // Sự kiện xoá dòng
    $('#table-dapan tbody').on('click', '.btn-xoa', function () {
      tableDapAn.row($(this).parents('tr')).remove().draw();
    });
    $("#btn-luuCauHoi").click(function () {
      $.LoadingOverlay("show");
      var tenCauHoi = $("#tenCauHoi").val();
      var idBaiTap = $("#otp-BaiTap").val();
      var tableData = [];

      // Lấy dữ liệu trong DataTable
      tableDapAn.rows().every(function () {
        var data = this.data(); // [tên đáp án, đúng/sai, xóa]
        tableData.push({
          tenDapAn: data[0],
          dapAnDung: data[1]
        });
      });
      $.ajax({
        url: "<%= request.getContextPath() %>/ThemCauHoi",
        type: "POST",
        data: {
          idBaiTap: idBaiTap,
          tenCauHoi: tenCauHoi,
          danhSachDapAn: JSON.stringify(tableData)
        },
        success: function (response) {
          console.log(response);
          $("#modalThemCauHoi").modal("hide");
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
