<%@ page import="com.quizizz.english.quizizz_english.model.ChuDe" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
  List<ChuDe> chuDes = (List<ChuDe>) request.getAttribute("chuDes");
//  int idChuDe = (int) request.getAttribute("idChuDe");
%>

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



  .text-ellipsis {
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }

  .text-ellipsis-2 {
    display: -webkit-box;
    -webkit-line-clamp: 2; /* số dòng muốn hiển thị */
    -webkit-box-orient: vertical;
    overflow: hidden;
  }
  .card-body {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 200px;
  }

  .topic-title {
    font-weight: bold;
    font-size: 1.1rem;
    margin-bottom: 5px;
    height: 30px; /* CHIỀU CAO CỐ ĐỊNH CHO TIÊU ĐỀ */
    line-height: 1.3;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  .topic-desc {
    font-size: 0.9rem;
    color: #666;
    margin-bottom: 10px;
    height: 40px; /* CHIỀU CAO CỐ ĐỊNH CHO MÔ TẢ */
    line-height: 1.2;
    display: -webkit-box;
    -webkit-line-clamp: 2; /* Tối đa 2 dòng */
    -webkit-box-orient: vertical;
    overflow: hidden;
    text-align: center;
  }

  .topic-card {
    height: 100%;
    background: white;
    border-radius: 20px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s ease;
  }

  .topic-card:hover {
    transform: scale(1.03);
  }


</style>
<div class="container mt-4">
  <!-- Tiêu đề -->
  <div class="text-center bg-light p-4 rounded shadow-sm">
    <h1 class="fw-bold">Chào mừng đến với <span class="text-primary">EnglishTest</span></h1>
  </div>

  <!-- Phần danh sách chủ đề -->
  <div class="mt-4 text-center bg-light p-3 rounded shadow-sm">
    <h4 class="fw-semibold">📚 Các chủ đề</h4>
  </div>

  <!-- Grid hiển thị chủ đề -->
  <div class="row mt-4" id="topic-container">
    <% if (chuDes != null) {
      for (ChuDe cd : chuDes) { %>
    <div class="col-md-3 col-sm-6 mb-4 d-flex justify-content-center">
      <div class="card text-center p-3 topic-card shadow-sm"
           style="width: 18rem; border-radius: 20px; cursor: pointer;"
           onclick="window.location.href='BaiTap?idChuDe=<%= cd.getId() %>'">  <!-- 'home?action=denDanhSachBaiTap&title=<%= cd.getTenChuDe() %>' -->

        <img src="<%= cd.getHinhAnh() != null ? cd.getHinhAnh() : request.getContextPath() + "/images/default.jpg" %>"
             class="rounded-circle mx-auto d-block mt-2"
             alt="Avatar"
             style="width: 100px; height: 100px; object-fit: cover;">

        <div class="card-body d-flex flex-column justify-content-between">
          <!-- Tên chủ đề với chiều cao cố định -->

<%--          <h5 class="topic-title mt-2 mb-1" title="<%= cd.getTenChuDe() %>"><%= cd.getTenChuDe() %></h5>--%>
          <h5 class="topic-title mt-2 mb-1"
              title="<%= cd.getTenChuDe() %>"
              data-bs-toggle="tooltip"
              data-bs-placement="top">
            <%= cd.getTenChuDe() %>
          </h5>


          <!-- Mô tả cố định vị trí bắt đầu -->
          <p class="topic-desc"><%= cd.getMoTa() != null ? cd.getMoTa() : "Oxford University" %></p>

          <!-- Nút làm bài ở cuối -->
<%--          <button class="btn btn-dark w-100 mt-auto" value="<%= cd.getId() %>" <%= cd.getId() == idChuDe %>>Chi tiết</button>--%>
<%--            <button class="btn btn-dark w-100 mt-auto"--%>
<%--                    onclick="window.location.href='<%= request.getContextPath() %>/BaiTap?idChuDe=<%= cd.getId() %>'">--%>
<%--                Chi tiết--%>
<%--            </button>--%>
<%--            <a href="<%= request.getContextPath() %>/BaiTap?idChuDe=<%= cd.getId() %>">Chi tiết</a>--%>
            <a href="<%= request.getContextPath() %>/BaiTap?idChuDe=<%= cd.getId() %>&idCapDo=1">Chi tiết</a>

        </div>

      </div>
    </div>


    <% }
    } else { %>
    <p>Không có chủ đề nào!</p>
    <% } %>
  </div>
    

</div>


<script>
  var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
  var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
  });
</script>

<%--<script>--%>
<%--  $(document).ready(function () {--%>
<%--    $.ajax({--%>
<%--      url: "<%= request.getContextPath() %>/home", // API lấy danh sách chủ đề--%>
<%--      type: "POST",--%>
<%--      dataType: "json",--%>
<%--      success: function (topics) {--%>
<%--        if (!topics || topics.length === 0) {--%>
<%--          console.warn("Không có dữ liệu topics.");--%>
<%--          return;--%>
<%--        }--%>

<%--        $('#topic-container').empty(); // Xóa nội dung cũ--%>


<%--        topics.forEach(topic => {--%>
<%--          let card = $('<div>').addClass('col-md-3 col-sm-6 mb-4');--%>

<%--          let cardInner = $('<div>').addClass('card shadow-sm topic-card')--%>
<%--                  .css('cursor', 'pointer') // Đổi con trỏ chuột để hiển thị có thể click--%>
<%--                  .click(function () {--%>
<%--                    window.location.href = "home?action=denDanhSachBaiTap&title=" + encodeURIComponent(topic.title);--%>
<%--                  });--%>

<%--          let img = $('<img>')--%>
<%--                  .addClass('card-img-top')--%>
<%--                  .attr('src', topic.image)--%>
<%--                  .attr('alt', topic.title)--%>
<%--                  .on('error', function () {--%>
<%--                    $(this).attr('src', '<%= request.getContextPath() %>/images/default.jpg');--%>
<%--                  });--%>

<%--          let cardBody = $('<div>').addClass('card-body text-center');--%>
<%--          let title = $('<h5>').addClass('card-title').text(topic.title);--%>

<%--          cardBody.append(title);--%>
<%--          cardInner.append(img).append(cardBody);--%>
<%--          card.append(cardInner);--%>

<%--          $('#topic-container').append(card);--%>
<%--        });--%>
<%--      },--%>
<%--      error: function () {--%>
<%--        alert("Lỗi khi tải danh sách chủ đề!");--%>
<%--      }--%>
<%--    });--%>
<%--  });--%>

<%--</script>--%>
