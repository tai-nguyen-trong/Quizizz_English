
<%--  <style>--%>
<%--    .correct { color: green; font-weight: bold; }--%>
<%--    .wrong { color: red; font-weight: bold; }--%>
<%--  </style>--%>

<%--  <h2>Kết quả chi tiết</h2>--%>
<%--  <table border="1" cellpadding="10">--%>
<%--    <tr>--%>
<%--      <th>#</th>--%>
<%--      <th>ID Câu hỏi</th>--%>
<%--      <th>Đáp án bạn chọn</th>--%>
<%--      <th>Đáp án đúng</th>--%>
<%--      <th>Đánh giá</th>--%>
<%--    </tr>--%>
<%--    <c:forEach var="r" items="${ketQuas}" varStatus="stt">--%>
<%--      <tr>--%>
<%--        <td>${stt.index + 1}</td>--%>
<%--        <td>${r.idCauHoi}</td>--%>
<%--        <td>${r.dapAnNguoiDungChon}</td>--%>
<%--        <td>${r.dapAnDung}</td>--%>
<%--        <td class="${r.cauDung ? 'correct' : 'wrong'}">--%>
<%--            ${r.cauDung ? 'Đúng' : 'Sai'}--%>
<%--        </td>--%>
<%--      </tr>--%>
<%--    </c:forEach>--%>
<%--  </table>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
  .answer-option {
    padding: 5px;
    margin-left: 20px;
  }

  .answer-option input[type="radio"]:checked + span {
    font-weight: bold;
  }

  .correct-choice input[type="radio"]:checked + span::before {
    content: "✔ ";
    color: green;
    font-weight: bold;
  }

  .wrong-choice input[type="radio"]:checked + span::before {
    content: "✖ ";
    color: red;
    font-weight: bold;
  }

  .question-box {
    padding: 20px;
    border-radius: 10px;
    background: #fff;
    margin-bottom: 20px;
    box-shadow: 0 0 5px rgba(0,0,0,0.1);
  }

  .result-text {
    font-weight: bold;
    margin-top: 10px;
  }

  .result-text.correct {
    color: green;
  }

  .result-text.wrong {
    color: red;
  }
</style>

<div class="header-info">
  <div class="row w-100">
    <div class="col-md-6"><span>📋 Chủ đề:</span> Ngữ pháp cơ bản</div>
    <div class="col-md-6"><span>🕐 Thời gian làm bài:</span> 16:16</div>
  </div>
  <div class="mt-3">
    <span>🔢 Điểm số:</span> 5/10
  </div>
</div>

<br>

<div id="question-list">

  <!-- Câu hỏi 1 - Người dùng chọn A (sai), đáp án đúng là B -->
  <div class="question-box" id="question-1">
    <div class="question-title">Câu 1: What is the past tense of "go"?</div>

    <div class="answer-option">
      <label><input type="radio" disabled checked /> <span>A. goes</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>B. went</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>C. going</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>D. go</span></label>
    </div>

    <div class="result-text wrong">SAI: Đáp án đúng là B</div>
  </div>

  <!-- Câu hỏi 2 - Người dùng chọn A (đúng) -->
  <div class="question-box" id="question-2">
    <div class="question-title">Câu 2: Which one is a fruit?</div>

    <div class="answer-option">
      <label><input type="radio" disabled checked /> <span>A. Apple</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>B. Table</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>C. Chair</span></label>
    </div>
    <div class="answer-option">
      <label><input type="radio" disabled /> <span>D. Window</span></label>
    </div>

    <div class="result-text correct">ĐÚNG</div>
  </div>

</div>
