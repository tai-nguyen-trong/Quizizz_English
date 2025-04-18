<%@ page import="com.quizizz.english.quizizz_english.model.CauHoi" %>
<%@ page import="java.util.List" %>
<%@ page import="com.quizizz.english.quizizz_english.model.DapAn" %>
<%@ page import="com.quizizz.english.quizizz_english.model.BaiTap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    List<CauHoi> cauHois = (List<CauHoi>) request.getAttribute("cauHois");
    BaiTap baiTap = (BaiTap) request.getAttribute("baiTap");
%>
<style>
  .question-box {
    padding: 20px;
    border-radius: 10px;
    background: #fff;
    margin-bottom: 20px;
    box-shadow: 0 0 5px rgba(0,0,0,0.1);
  }
  .question-title {
    font-weight: bold;
    margin-bottom: 10px;
  }
  .answer-option {
    margin-bottom: 8px;
  }
  .question-nav .q-btn {
    width: 36px;
    height: 36px;
    border: none;
    border-radius: 6px;
    font-weight: bold;
    background-color: #e0e0e0;
    color: #333;
    transition: all 0.3s;
  }
  .header-info {
    margin-bottom: 20px;
    padding: 15px;
  }
  .header-info div {
    font-size: 16px;
    font-weight: bold;
  }
  .btn-group {
    text-align: center;
    margin-top: 30px;
  }
  .btn-submit-fixed {
    position: sticky;
    bottom: 0;
    background-color: #e0e0e0;
    padding: 10px 0;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 10;
  }
  .btn-submit {
    background-color: #007bff;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 8px;
    cursor: pointer;
  }
  .btn-submit:hover {
    background-color: #0056b3;
  }
  .q-btn {
      background-color: lightgray;
      border: none;
      padding: 8px 12px;
      margin: 4px;
      border-radius: 4px;
  }

  .q-btn.answered {
      background-color: #28a745;
      color: white;
  }
</style>

<%--<form method="post" action="NopBaiTap">--%>
<div class="header-info">
  <div class="row w-100">
      <input type="hidden" id="idBaiTap" value="<%= baiTap.getId() %>">
      <input type="hidden" id="idChuDe" value="<%= baiTap.getChuDe().getId() %>">
    <div class="col-md-6"><span>📋 Chủ đề:</span> <%= baiTap.getChuDe().getTenChuDe() %></div>
    <div class="col-md-6"><span>🕒 Thời gian:</span> <%= baiTap.getThoiGianLamBai() %></div>
  </div>
  <div class="mt-3">
    <span>☑️ Câu hỏi:</span>
    <div class="question-nav mt-2" id="question-status">
    <% int soThuTu = 1; %>
      <% for(CauHoi cauhoi : cauHois){ %>
        <button type="button" class="q-btn" id="q-<%= cauhoi.getId() %>" onclick="scrollToQuestion(${cauhoi.getId()})"><%= soThuTu %></button>
        <% soThuTu++ ; %>
      <% } %>
    </div>
  </div>
</div>

<div id="question-list">
      <% int stt = 1; %>
      <% for(CauHoi ch : cauHois) { %>
      <div class="question-box" id="question-<%= ch.getId() %>">
          <div class="question-title">Câu <%= stt++ %>: <%= ch.getTenCauHoi() %></div>
          <%
              List<DapAn> dapAns = ch.getDapAn();
              char option = 'A';
              for (DapAn da : dapAns) {
          %>
          <div class="answer-option">
              <label>
                  <input type="radio" name="q<%= ch.getId() %>" value="<%= da.getId() %>" onchange="markDone(<%= ch.getId() %>)"/>
                  <%= option++ %>. <%= da.getTenDapAn() %>
              </label>
          </div>
          <% } %>
      </div>
      <% } %>
</div>

<div class="btn-submit-fixed">
  <button class="btn-submit" id="btn-nopBai">✔ Nộp bài</button>
</div>
<%--</form>--%>

<script>
    function markDone(questionId) {
        const radios = document.getElementsByName("q" + questionId);
        let answered = false;

        for (const radio of radios) {
            if (radio.checked) {
                answered = true;
                break;
            }
        }

        const btn = document.getElementById("q-" + questionId);
        if (answered) {
            btn.classList.add("answered");
        } else {
            btn.classList.remove("answered");
        }
    }
    $(document).ready(function() {
        $("#btn-nopBai").on("click",function () {
            const danhSachDapAn = {};
            $('input[type="radio"]:checked').each(function () {
                const questionId = $(this).attr('name').replace('q', '');
                const answerId = $(this).val();
                danhSachDapAn[questionId] = parseInt(answerId);
            });
            const postData = {
                idBaiTap: $('#idBaiTap').val(),
                idChuDe: $('#idChuDe').val(),
                danhSachDapAn: JSON.stringify(danhSachDapAn)
            };
            debugger;
            $.ajax({
                url: '<%= request.getContextPath() %>/NopBaiTap',
                method: 'POST',
                data: postData,
                success: function (res, status, xhr) {

                },
                error: function (xhr, status, err) {
                    console.error('Lỗi khi nộp bài:', err);
                    alert('Đã có lỗi xảy ra khi nộp bài!');
                }
            });
        });

    });
</script>
