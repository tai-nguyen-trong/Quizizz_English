<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- CSS chỉ dùng thử nghiệm, bạn có thể tích hợp vào layout chính -->
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

  .question-nav {
    display: flex;
    flex-wrap: wrap;
    gap: 6px;
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

  .question-nav .q-btn.done {
    background-color: #4CAF50;
    color: white;
  }

  .header-info {
    display: flex;
    justify-content: space-between;
    margin-bottom: 20px;
    padding: 15px;
    background: #f5f5f5;
    border-radius: 10px;
  }

  .header-info div {
    font-size: 16px;
    font-weight: bold;
  }

  .btn-group {
    text-align: center;
    margin-top: 30px;
  }

  .btn-next, .btn-submit {
    padding: 10px 20px;
    font-size: 16px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    margin: 0 5px;
  }



  .header-info {
    margin-bottom: 20px;
    padding: 15px;
    background: #f5f5f5;
    border-radius: 10px;
  }

  /* Ghim nút ở giữa phần content (không tính sidebar) */
  .btn-submit-fixed {
    position: fixed;
    bottom: 20px;
    left: calc(260px + 50%); /* 260px là sidebar */
    transform: translateX(-50%);
    z-index: 999;
  }

  .btn-submit {
    padding: 10px 20px;
    background-color: #198754;
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 18px;
    font-weight: bold;
    box-shadow: 0 2px 6px rgba(0,0,0,0.2);
    cursor: pointer;
  }


</style>

<!-- Header thông tin -->
<%--<div class="header-info">--%>
<%--  <div>📋 Chủ đề: <span id="exam-topic">Đọc hiểu từ vựng</span></div>--%>
<%--  <div>🕒 Thời gian: <span id="exam-timer">30:00</span></div>--%>
<%--  <div>--%>
<%--    ☑️ Câu hỏi:--%>
<%--    <div class="question-nav" id="question-status">--%>
<%--      <c:forEach var="i" begin="1" end="4">--%>
<%--        <button type="button" class="q-btn" id="q-${i}" onclick="scrollToQuestion(${i})">${i}</button>--%>
<%--      </c:forEach>--%>
<%--    </div>--%>
<%--  </div>--%>
<%--</div>--%>
<div class="header-info">
  <div class="row w-100">
    <!-- Hàng 1 -->
    <div class="col-md-6"><span>📋 Chủ đề:</span> <span id="exam-topic">Đọc hiểu từ vựng</span></div>
    <div class="col-md-6"><span>🕒 Thời gian:</span> <span id="exam-timer">30:00</span></div>
  </div>
  <!-- Hàng 2 -->
  <div class="mt-3">
    <span>☑️ Câu hỏi:</span>
    <div class="question-nav mt-2" id="question-status">
      <c:forEach var="i" begin="1" end="4">
        <button type="button" class="q-btn" id="q-${i}" onclick="scrollToQuestion(${i})">${i}</button>
      </c:forEach>
    </div>
  </div>
</div>

<!-- Danh sách câu hỏi -->
<div id="question-list">
  <c:forEach var="q" begin="1" end="4">
    <div class="question-box" id="question-${q}">
      <div class="question-title">Question ${q}.</div>
      <div class="answer-option">
        <label><input type="radio" name="q${q}" value="A" onchange="markDone(${q})"/> A. Đáp án A</label>
      </div>
      <div class="answer-option">
        <label><input type="radio" name="q${q}" value="B" onchange="markDone(${q})"/> B. Đáp án B</label>
      </div>
      <div class="answer-option">
        <label><input type="radio" name="q${q}" value="C" onchange="markDone(${q})"/> C. Đáp án C</label>
      </div>
      <div class="answer-option">
        <label><input type="radio" name="q${q}" value="D" onchange="markDone(${q})"/> D. Đáp án D</label>
      </div>
    </div>
  </c:forEach>
</div>

<!-- Nút chuyển câu hỏi -->
<%--<div class="btn-group">--%>
<%--  <button class="btn-submit" onclick="submitExam()">✔ Nộp bài</button>--%>
<%--</div>--%>
<div class="btn-submit-fixed">
  <button class="btn-submit" onclick="submitExam()">✔ Nộp bài</button>
</div>

<!-- JavaScript xử lý -->
<script>
  // Giả lập thời gian đếm ngược
  let totalTime = 30 * 60; // 30 phút
  const timerDisplay = document.getElementById('exam-timer');

  function updateTimer() {
    const minutes = Math.floor(totalTime / 60);
    const seconds = totalTime % 60;
    timerDisplay.textContent = `${minutes}:${seconds < 10 ? '0' : ''}${seconds}`;
    if (totalTime > 0) {
      totalTime--;
      setTimeout(updateTimer, 1000);
    } else {
      alert("Hết giờ! Tự động nộp bài.");
      submitExam();
    }
  }
  updateTimer();

  // Đánh dấu câu đã làm
  function markDone(qNumber) {
    document.getElementById(`q-${qNumber}`).classList.add("done");
  }

  // Cuộn đến câu hỏi
  function scrollToQuestion(qNumber) {
    const qEl = document.getElementById(`question-${qNumber}`);
    qEl.scrollIntoView({ behavior: "smooth", block: "start" });
  }


  function submitExam() {
    if (confirm("Bạn chắc chắn muốn nộp bài?")) {
      alert("Đã nộp bài!");
      // Bạn có thể submit form hoặc redirect tại đây
    }
  }
</script>
