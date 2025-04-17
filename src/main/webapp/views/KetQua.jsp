
  <style>
    .correct { color: green; font-weight: bold; }
    .wrong { color: red; font-weight: bold; }
  </style>

  <h2>Kết quả chi tiết</h2>
  <table border="1" cellpadding="10">
    <tr>
      <th>#</th>
      <th>ID Câu hỏi</th>
      <th>Đáp án bạn chọn</th>
      <th>Đáp án đúng</th>
      <th>Đánh giá</th>
    </tr>
    <c:forEach var="r" items="${ketQuas}" varStatus="stt">
      <tr>
        <td>${stt.index + 1}</td>
        <td>${r.idCauHoi}</td>
        <td>${r.dapAnNguoiDungChon}</td>
        <td>${r.dapAnDung}</td>
        <td class="${r.cauDung ? 'correct' : 'wrong'}">
            ${r.cauDung ? 'Đúng' : 'Sai'}
        </td>
      </tr>
    </c:forEach>
  </table>

