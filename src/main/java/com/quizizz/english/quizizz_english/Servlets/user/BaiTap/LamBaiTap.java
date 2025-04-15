package com.quizizz.english.quizizz_english.Servlets.user.BaiTap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LamBaiTap")
public class LamBaiTap extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("currentPage", "LamBaiTap");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/layouts/layoutUser.jsp");
        dispatcher.forward(req, resp);
    }
}
