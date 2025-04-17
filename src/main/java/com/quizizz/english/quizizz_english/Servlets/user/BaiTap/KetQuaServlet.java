package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import com.quizizz.english.quizizz_english.dto.KetQuaDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/KetQua")
public class KetQuaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Lấy dữ liệu từ session
        List<KetQuaDTO> ketQuas = (List<KetQuaDTO>) session.getAttribute("ketQuas");

        // Xóa khỏi session nếu muốn không lưu nữa
        session.removeAttribute("ketQuas");

        // Gửi tới trang JSP hiển thị kết quả
        request.setAttribute("ketQuas", ketQuas);
        request.setAttribute("currentPage", "KetQua");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(request, response);
    }
}
