<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
          </div>
      </div>

      <!-- Footer có 2 nút: Lưu thông tin và Đóng -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="submit" class="btn btn-success" id="btnLuuChuDe">Lưu thông tin</button>
      </div>
    </div>
  </div>
</div>


<script>
  $(document).ready(function () {
    $.ajax({
      url: "<%= request.getContextPath() %>/ChuDe", // API lấy danh sách chủ đề
      type: "GET",
      dataType: "json",
      success: function (topics) {
        if (!topics || topics.length === 0) {
          console.warn("Không có dữ liệu topics.");
          return;
        }

        $('#topic-container').empty(); // Xóa nội dung cũ

        topics.forEach(topic => {
          let card = $('<div>').addClass('col-md-3 col-sm-6 mb-4');

          let cardInner = $('<div>').addClass('card shadow-sm topic-card')
                  .css('cursor', 'pointer') // Đổi con trỏ chuột để hiển thị có thể click
                  .click(function () {
                    alert("Bạn đã chọn chủ đề: " + topic.title);
                  });

          let img = $('<img>')
                  .addClass('card-img-top')
                  .attr('src', topic.image)
                  .attr('alt', topic.title)
                  .on('error', function () {
                    $(this).attr('src', '<%= request.getContextPath() %>/images/default.jpg');
                  });

          let cardBody = $('<div>').addClass('card-body text-center');
          let title = $('<h5>').addClass('card-title').text(topic.title);

          cardBody.append(title);
          cardInner.append(img).append(cardBody);
          card.append(cardInner);

          $('#topic-container').append(card);
        });
      },
      error: function () {
        alert("Lỗi khi tải danh sách chủ đề!");
      }
    });

    // Xử lý sự kiện khi nhấn nút "Thêm chủ đề"
    $("#btnThemChuDe").click(function () {
      $("#modalThemChuDe").modal("show");
    });
  });
</script>
