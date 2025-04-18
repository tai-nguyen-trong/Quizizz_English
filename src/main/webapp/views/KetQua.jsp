
<%@ page import="com.google.gson.Gson" %>
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
  .question-box {
    border: 1px solid #ccc;
    padding: 15px;
    margin-bottom: 20px;
    border-radius: 10px;
  }

  .correct {
    border-left: 5px solid green;
    color: green;
  }

  .wrong {
    border-left: 5px solid red;
    color: red;
  }

  .result-text {
    margin-top: 10px;
    font-weight: bold;
  }
</style>

<div class="header-info">
  <div class="row w-100">
    <div class="col-md-6">
      <span>📋 Chủ đề:</span> <span id="tenChuDe">Ngữ pháp cơ bản</span>
    </div>
    <div class="col-md-6">
      <span>📘 Bài tập:</span> <span id="tenBaiTap">Bài tập 1</span>
    </div>
  </div>
  <div class="mt-3">
    <span>🔢 Điểm số:</span> <span id="diemSo">5/10</span>
  </div>
</div>
<br>
<div id="question-list">

</div>
<script>
  $(document).ready(function () {
    var ketQuasData = <%= new Gson().toJson(request.getAttribute("ketQuas")) %>;
    renderCauHoi(ketQuasData);
    function renderCauHoi(data) {
      $("#tenBaiTap").text(data[0].tenBaiTap);
      $("#tenChuDe").text(data[0].tenChuDe);
      $("#diemSo").text(data[0].soDiem + "/10");
      const container = $("#question-list");
      container.empty(); // Xóa nội dung cũ
      data.forEach((item, index) => {
        console.log(index);
        const isCorrect = item.cauDung === true;
        const cssClass = isCorrect ? "correct" : "wrong";

        // Tạo phần tử container cho câu hỏi
        let questionBox = $('<div>', {
          class: `question-box ${cssClass}`,
          id: `question-${item.idCauHoi}`
        });

        // Tạo phần tử tiêu đề câu hỏi
        let questionTitle = $('<div>', {
          class: 'question-title',
          text: 'Câu ' + (index + 1) + ': ' + item.tenCauHoi
        });

        // Tạo phần tử đáp án người dùng chọn
        let answerUser = $('<div>', {
          class: 'answer-user'
        }).append(
                $('<strong>').text('Đáp án của bạn:'),
                $('<span>').html(item.dapAnNguoiDungChon || '<i style="color: gray;">(Chưa chọn)</i>')
        );

        // Tạo phần tử đáp án đúng
        let answerCorrect = $('<div>', {
          class: 'answer-correct'
        }).append(
                $('<strong>').text('Đáp án đúng:'),
                $('<span>').text(item.dapAnDung)
        );

        // Tạo phần tử kết quả
        let resultText = $('<div>', {
          class: `result-text ${cssClass}`,
          text: isCorrect ? '✔ Bạn đã trả lời đúng!' : '✘ Bạn đã trả lời sai'
        });

        // Thêm các phần tử vào questionBox
        questionBox.append(questionTitle, answerUser, answerCorrect, resultText);
        // Thêm questionBox vào #question-list
        $('#question-list').append(questionBox);
      });
    }
    // Gọi hàm render khi trang đã sẵn sàng

  });
</script>
