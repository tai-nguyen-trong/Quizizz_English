<%@ page import="com.quizizz.english.quizizz_english.model.ChuDe" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<ChuDe> chuDes = (List<ChuDe>) request.getAttribute("chuDes");
    Gson gson = new Gson();
    String chuDesJson = gson.toJson(chuDes);
%>

<script>
    // Gán chuDes từ server sang biến JavaScript
    const topics = <%= chuDesJson %>;
</script>
<style>
  .topic-card {
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    border: none;
    cursor: pointer;
  }
  .topic-card:hover {
    transform: translateY(-5px);
    box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.2);
  }
  .card-img-top {
    height: 150px;
    object-fit: cover;
    border-radius: 10px 10px 0 0;
  }
</style>

<div class="container mt-4">
  <!-- Tiêu đề -->
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h4><i class="fas fa-book"></i> Các chủ đề</h4>
    <button id="btnThemChuDe" class="btn btn-primary">Thêm chủ đề</button>
  </div>

  <!-- Grid hiển thị chủ đề -->
  <div class="row mt-4" id="topic-container"></div>
</div>

<!-- Modal Thêm Chủ Đề -->
<div class="modal fade" id="modalThemChuDe" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Thêm Chủ Đề Mới</h5>
      </div>
      <div class="modal-body">
          <!-- Nhập Tên Chủ Đề -->
          <div class="form-group">
            <label for="tenChuDe">Tên Chủ Đề</label>
            <input type="text" class="form-control" id="tenChuDe" placeholder="Nhập tên chủ đề" required>
          </div>

          <!-- Nhập Mô Tả -->
          <div class="form-group">
            <label for="moTaChuDe">Mô Tả</label>
            <textarea class="form-control" id="moTaChuDe" rows="3" placeholder="Nhập mô tả chủ đề"></textarea>
          </div>

          <!-- Chọn Hình Ảnh -->
          <div class="form-group mt-3">
            <label for="anhChuDe">Hình ảnh</label>
            <input type="file" class="form-control-file" id="anhChuDe">
              <img id="previewImage" src="" alt="Xem trước ảnh" class="mt-3" style="max-width: 300px; display: none; border-radius: 12px;">
          </div>
      </div>

      <!-- Footer có 2 nút: Lưu thông tin và Đóng -->
      <div class="modal-footer">
        <button type="button" class="btn btn-success" id="btnLuuChuDe">Thêm Chủ Đề</button>
        <button type="button" class="btn btn-success" id="btn-CapNhat">Cập nhật</button>
          <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Đóng</button>
      </div>
    </div>
  </div>
</div>


<script>
  $(document).ready(function () {
      // biê
      let base64Image = "";
      let idChuDe = "";
    //load dữ liệu danh sách chủ đề
      danhSachChuDe();
      function danhSachChuDe() {
          $('#topic-container').empty(); // Xóa nội dung cũ
          topics.forEach(topic => {
              let card = $('<div>').addClass('col-md-3 col-sm-6 mb-4');

              let cardInner = $('<div>').addClass('card shadow-sm topic-card position-relative overflow-hidden')
                  .css({
                      'cursor': 'pointer',
                      'border-radius': '12px',
                      'transition': 'all 0.3s ease-in-out'
                  })
                  .click(function () {
                      alert("Bạn đã chọn chủ đề: " + topic.tenChuDe);
                  });

              // Nút Sửa & Xóa (Luôn hiển thị)
              let actionButtons = $('<div>')
                  .addClass('action-buttons position-absolute top-0 end-0 p-2')
                  .css({
                      'background': 'rgba(0, 0, 0, 0.6)',
                      'border-radius': '8px',
                      'padding': '4px'
                  });

              let editBtn = $('<button>')
                  .addClass('btn btn-sm btn-warning me-1 rounded-circle')
                  .html('<i class="material-icons">edit</i>')
                  .css({
                      'width': '32px', 'height': '32px', 'display': 'flex',
                      'align-items': 'center', 'justify-content': 'center'
                  })
                  .click(function (e) {
                      e.stopPropagation();
                      $("#modalThemChuDe").modal("show");
                      $("#btn-CapNhat").show();
                      $("#btnLuuChuDe").hide();
                      lamMoiGiaTri();
                      $("#tenChuDe").val(topic.tenChuDe);
                      $("#moTaChuDe").val(topic.moTa);
                      base64Image = topic.hinhAnh;
                      $('#previewImage')
                          .attr('src', topic.hinhAnh)
                          .show();
                      idChuDe = topic.id;

                  });

              let deleteBtn = $('<button>')
                  .addClass('btn btn-sm btn-danger rounded-circle')
                  .html('<i class="material-icons">delete</i>')
                  .css({
                      'width': '32px', 'height': '32px', 'display': 'flex',
                      'align-items': 'center', 'justify-content': 'center'
                  })
                  .click(function (e) {
                      e.stopPropagation();
                      if (confirm("Bạn có chắc chắn muốn xóa " + topic.tenChuDe + " không?")) {
                          $(card).fadeOut(300, function() { $(this).remove(); });
                          xoaChuDe(topic.id);
                      }
                  });

              actionButtons.append(editBtn).append(deleteBtn);

              let img = $('<img>')
                  .addClass('card-img-top')
                  .attr('src',topic.hinhAnh) // ✅ chèn base64
                  .attr('alt', topic.tenChuDe)
                  .css({
                      'border-top-left-radius': '12px',
                      'border-top-right-radius': '12px'
                  })
                  .on('error', function () {
                      $(this).attr('src', '<%= request.getContextPath() %>/images/default.jpg');
                  });

              let cardBody = $('<div>').addClass('card-body text-center bg-white')
                  .css({
                      'border-bottom-left-radius': '12px',
                      'border-bottom-right-radius': '12px',
                      'padding': '15px'
                  });

              let title = $('<h5>').addClass('card-title').text(topic.tenChuDe)
                  .css({ 'font-weight': 'bold', 'margin-bottom': '5px' });

              let description = $('<p>').addClass('text-muted')
                  .text(topic.moTa)
                  .css({
                      'font-size': '14px',
                      'min-height': '40px'
                  });

              cardBody.append(title).append(description);
              cardInner.append(actionButtons).append(img).append(cardBody);
              card.append(cardInner);

              $('#topic-container').append(card);
          });
      }

    // Xử lý sự kiện khi nhấn nút "Thêm chủ đề"
    $("#btnThemChuDe").click(function () {
      $("#modalThemChuDe").modal("show");
        $("#btn-CapNhat").hide();
        $("#btnLuuChuDe").show();
    });
     $("#btnLuuChuDe").click(async function () {
         const tenChuDe = $('#tenChuDe').val().trim();
         const moTa = $('#moTaChuDe').val().trim();
         const fileInput = $('#anhChuDe')[0];
         const file = fileInput.files[0];
         if (!file) return alert("Vui lòng chọn ảnh!");
          base64Image = await getBase64(file);
         $.ajax({
             url: "<%= request.getContextPath() %>/ThemChuDe",
             type: "POST",
             data: {
                 tenChuDe: tenChuDe,
                 moTa: moTa,
                 hinhAnh: base64Image
             },
             success: function (response) {
                 console.log(response);
                 $("#modalThemChuDe").modal("hide");
                 $.LoadingOverlay("hide");
                 location.reload(true);
             },
             error: function () {
                 console.log("them that bai");
                 $.LoadingOverlay("hide");
             }
         });
     });
     // hieenr thị khi chọn ảnh
      $('#anhChuDe').on('change', function () {
          const file = this.files[0];
          if (file) {
              const reader = new FileReader();
              reader.onload = function (e) {
                  $('#previewImage')
                      .attr('src', e.target.result)
                      .show(); // Hiện ảnh
              };
              reader.readAsDataURL(file);
          }
      });
      // cập nật chủ đề
      $("#btn-CapNhat").click(async function () {
          const tenChuDe = $('#tenChuDe').val().trim();
          const moTa = $('#moTaChuDe').val().trim();
          const fileInput = $('#anhChuDe')[0];
          const file = fileInput.files[0];
          if (file) {
              base64Image = await getBase64(file);
          }

          $.ajax({
              url: "<%= request.getContextPath() %>/CapNhatChuDe",
              type: "PUT",
              data: JSON.stringify({
                  id: idChuDe,
                  tenChuDe: tenChuDe,
                  moTa: moTa,
                  hinhAnh: base64Image,
              }),
              success: function (response) {
                  $("#modalThemChuDe").modal("hide");
                  $.LoadingOverlay("hide");
                  location.reload(true);
              },
              error: function () {
                  console.log("them that bai");
                  $.LoadingOverlay("hide");
              }
          });
      });
      // xóa chủ đề
    function xoaChuDe(idChude) {
        $.ajax({
            url: "<%= request.getContextPath() %>/XoaChuDe?idChuDe=" + idChude,
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
    // base64 hình ảnh
      function getBase64(file) {
          return new Promise((resolve, reject) => {
              const reader = new FileReader();
              reader.onload = () => resolve(reader.result); // base64 data:image/jpeg;base64,...
              reader.onerror = reject;
              reader.readAsDataURL(file);
          });
      }
      // làm mới các giá trị input
      function lamMoiGiaTri() {
          $('#tenChuDe').val("");
          $('#moTaChuDe').val("");
          $('#anhChuDe').val("");
          base64Image= "";
      }

  });
</script>
