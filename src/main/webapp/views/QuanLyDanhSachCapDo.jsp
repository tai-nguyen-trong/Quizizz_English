
<%@ page import="java.util.List" %>
<%@ page import="com.quizizz.english.quizizz_english.model.CapDo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
    <h2 class="mb-0">Danh sách  cấp độ</h2>
    <button id="btn-ThemBaiTap" class="btn btn-success text-white fw-bold">Thêm cấp độ mới</button>
  </div>
  <!-- Bảng danh sách bài tập -->
  <div class="table-responsive">
    <table class="table table-striped table-bordered" id="exerciseTable" style="width: 100%">
      <thead>
      <tr>
        <th>Id</th>
        <th>Tên cấp độ</th>
        <th style="text-align: center;">Xóa</th>
      </tr>
      </thead>
      <tbody>
      </tbody>
    </table>
  </div>
</div>
<!-- Modal Thêm bài tập  -->
<div class="modal fade" id="modalThemCapDo" tabindex="-1" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 40%;">
    <div class="modal-content">
      <!-- Header -->
      <div class="modal-header bg-primary text-white">
        <h5 class="modal-title" id="modalLabel">Thêm Cấp Độ Mới</h5>
        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>

      <!-- Body -->
      <div class="modal-body">
        <div class="container">
          <div class="row">
            <!-- Tên Bài Tập -->
            <div class="col-md-12">
              <label class="fw-bold">Tên Cấp Độ</label>
              <input id="tenCapDo" type="text" class="form-control">
            </div>

          </div>
        </div>
      </div>
      <!-- Footer -->
      <div class="modal-footer">
        <button id="btn-themThongTin" type="button" class="btn btn-success">Thêm Cấp Độ</button>
        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>


<script>
  $(document).ready(function () {
    //biến
    let capDos = [
      <% if (capDos != null) {
           for (CapDo cd : capDos) { %>
      {
        id: <%= cd.getId() %>,
        tenCapDo: "<%= cd.getTenCapDo() %>",
      },
      <%   }
         } %>
    ];
    debugger;
    // Kích hoạt DataTables
    var exerciseTable = $("#exerciseTable").DataTable(
            {
              "processing": true,
              dom: 'Bfrtip',
              columnDefs: [
                { targets: [0] , visible: false },
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
    danhSachCapDo();
    // Xử lý sự kiện khi nhấn nút "Thêm chủ đề"
    $("#btn-ThemBaiTap").click(function () {
      $("#modalThemCapDo").modal("show");
      $("#tenCapDo").val("");
    });
    // thêm bài tập
    $('#btn-themThongTin').on('click',function (){
      $.LoadingOverlay("show");
      var tenCapDo = $("#tenCapDo").val();
      $.ajax({
        url: "<%= request.getContextPath() %>/ThemCapDo",
        type: "POST",
        data: {
          tenCapDo: tenCapDo,
        },
        success: function (response) {
          $("#modalThemCapDo").modal("hide");
          location.reload(true);
          $.LoadingOverlay("hide");
        },
        error: function () {
          console.log("them that bai");
          $.LoadingOverlay("hide");
        }
      });
    });
// Sự kiện xoá dòng
    $('#exerciseTable tbody').on('click', '.btn-xoa', function () {
      var table = $('#exerciseTable').DataTable();
      var row = $(this).closest('tr');
      var rowData = table.row(row).data();
      var id = rowData[0];
      // Xác nhận trước khi xóa
      if (confirm("Bạn có chắc muốn xóa đáp án này?")) {
        table.row(row).remove().draw(); // Xóa khỏi DataTable (trên giao diện)
        $.ajax({
          url: '<%= request.getContextPath() %>/XoaCapDo?idCapDo=' + id,
          type: 'DELETE',
          success: function () {
            alert("Xóa thành công!");
            location.reload(true);
          },
          error: function () {
            alert("Lỗi khi xóa!");
          }
        });
      }
    });
    function danhSachCapDo() {
      exerciseTable.clear().draw();
      let btnXoa = "<div class='d-flex justify-content-center align-items-center'>" +
              "<button class='btn bg-blue waves-effect d-flex justify-content-center align-items-center btn-xoa'>" +
              "<i class='material-icons'>delete</i>" +
              "</button></div>";
      capDos.forEach(function (item) {
        exerciseTable.row.add([
          item.id,
          item.tenCapDo,
          btnXoa
        ]);
      });
      exerciseTable.draw();
    }
  });
</script>
