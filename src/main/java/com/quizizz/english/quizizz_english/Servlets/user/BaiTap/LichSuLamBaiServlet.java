package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LichSuLamBai")
public class LichSuLamBaiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Lấy tất cả lịch sử làm bài của user
        req.setAttribute("currentPage", "LichSuLamBai");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }
}
