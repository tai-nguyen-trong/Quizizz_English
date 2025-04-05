package com.quizizz.english.quizizz_english.Servlets;

import com.quizizz.english.quizizz_english.model.NguoiDung;
import com.quizizz.english.quizizz_english.repositoryImpl.NguoiDungRepositoryImpl;
import com.quizizz.english.quizizz_english.service.INguoiDungService;
import com.quizizz.english.quizizz_english.serviceImpl.NguoiDungServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.System.out;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
private INguoiDungService nguoiDungService;

    @Override
    public void init() throws ServletException {
        nguoiDungService = new NguoiDungServiceImpl(new NguoiDungRepositoryImpl());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String email = req.getParameter("email");
        String matKhau = req.getParameter("matKhau");

        NguoiDung nguoiDung = nguoiDungService.dangNhap(email, matKhau);
        if (nguoiDung != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", nguoiDung);
            resp.sendRedirect(req.getContextPath() + "/layouts/layout.jsp");
        } else {
            req.setAttribute("error", "Sai email hoặc mật khẩu.");
            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
        }
    }
}

